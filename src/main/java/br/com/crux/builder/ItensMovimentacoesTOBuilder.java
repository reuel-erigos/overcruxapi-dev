package br.com.crux.builder;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.cmd.GetCategoriasContabeisCmd;
import br.com.crux.cmd.GetMaterialCmd;
import br.com.crux.cmd.GetPedidosMateriaisCmd;
import br.com.crux.cmd.GetTributosItensMovimentacaoCmd;
import br.com.crux.cmd.GetUsuarioLogadoCmd;
import br.com.crux.entity.CategoriasContabeis;
import br.com.crux.entity.ItensMovimentacoes;
import br.com.crux.entity.Material;
import br.com.crux.entity.Movimentacoes;
import br.com.crux.entity.PedidosMateriais;
import br.com.crux.to.ItensMovimentacoesTO;

@Component
public class ItensMovimentacoesTOBuilder {

	@Autowired private CategoriasContabeisTOBuilder categoriasContabeisTOBuilder;
	@Autowired private DepartamentoTOBuilder departamentoTOBuilder;
	@Autowired private MaterialTOBuilder materialTOBuilder;
	@Autowired private PedidosMateriaisTOBuilder pedidosMateriaisTOBuilder;
	@Autowired private GetPedidosMateriaisCmd getPedidosMateriaisCmd;
	@Autowired private UnidadeTOBuilder unidadeTOBuilder;
	@Autowired private GetUsuarioLogadoCmd getUsuarioLogadoCmd;
	@Autowired private GetCategoriasContabeisCmd getCategoriasContabeisCmd;
	@Autowired private GetMaterialCmd getMaterialCmd;
	@Autowired private GetTributosItensMovimentacaoCmd getTributosItensMovimentacaoCmd ;

	public ItensMovimentacoesTO buildTO(ItensMovimentacoes entity) {
		ItensMovimentacoesTO to = new ItensMovimentacoesTO();

		if (Objects.isNull(entity)) {
			return to;
		}
		
		BeanUtils.copyProperties(entity, to);

		to.setIdMovimentacao(entity.getId());
		to.setCategoria(categoriasContabeisTOBuilder.buildTOCombo(entity.getCategoria()));
		to.setCategoriaAdicional(categoriasContabeisTOBuilder.buildTOCombo(entity.getCategoriaAdicional()));
		to.setDepartamento(departamentoTOBuilder.buildTOCombo(entity.getDepartamento()));
		to.setMaterial(materialTOBuilder.buildTOCombo(entity.getMaterial()));
		to.setPedidosMateriais(pedidosMateriaisTOBuilder.buildTOCombo(entity.getPedidosMateriais()));
		to.setUnidade(unidadeTOBuilder.buildTOCombo(entity.getUnidade()));
		to.setTributos(getTributosItensMovimentacaoCmd.getAllTOByIdItemMovimentacao(entity.getId()));
		
		return to;
	}

	public List<ItensMovimentacoesTO> buildAll(List<ItensMovimentacoes> lista) {
		return lista.stream()
				.map(this::buildTO)
				.collect(Collectors.toList());
	}

	public ItensMovimentacoes build(Movimentacoes movimentacao, ItensMovimentacoesTO to) {
		ItensMovimentacoes entity = new ItensMovimentacoes();

		BeanUtils.copyProperties(to, entity);

		if (Objects.nonNull(to.getCategoria()) && Objects.nonNull(to.getCategoria().getId())) {
			CategoriasContabeis retorno = getCategoriasContabeisCmd.getById(to.getCategoria().getId());
			entity.setCategoria(retorno);
		}

		if (Objects.nonNull(to.getCategoriaAdicional()) && Objects.nonNull(to.getCategoriaAdicional().getId())) {
			CategoriasContabeis retorno = getCategoriasContabeisCmd.getById(to.getCategoriaAdicional().getId());
			entity.setCategoriaAdicional(retorno);
		}

		if (Objects.nonNull(to.getMaterial()) && Objects.nonNull(to.getMaterial().getId())) {
			Material retorno = getMaterialCmd.getById(to.getMaterial().getId());
			entity.setMaterial(retorno);
		}

		if (Objects.nonNull(to.getPedidosMateriais()) && Objects.nonNull(to.getPedidosMateriais().getId())) {
			PedidosMateriais retorno = getPedidosMateriaisCmd.getById(to.getPedidosMateriais().getId());
			entity.setPedidosMateriais(retorno);
		}
		
		entity.setIdMovimentacao(movimentacao.getId());
		entity.setUnidade(movimentacao.getUnidade());
		entity.setDepartamento(movimentacao.getDepartamento());
		entity.setUsuarioAlteracao(getUsuarioLogadoCmd.getUsuarioLogado().getIdUsuario());
		
		return entity;
	}
	
	public ItensMovimentacoesTO buildTOCombo(ItensMovimentacoes entity) {
		ItensMovimentacoesTO to = new ItensMovimentacoesTO();

		BeanUtils.copyProperties(entity, to);

		return to;
	}

	public List<ItensMovimentacoesTO> buildAllCombo(List<ItensMovimentacoes> lista) {
		return lista.stream()
				.map(this::buildTOCombo)
				.collect(Collectors.toList());
	}


}
