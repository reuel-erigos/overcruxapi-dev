package br.com.crux.builder;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.cmd.GetCategoriasContabeisCmd;
import br.com.crux.cmd.GetDepartamentoCmd;
import br.com.crux.cmd.GetMaterialCmd;
import br.com.crux.cmd.GetUnidadeCmd;
import br.com.crux.cmd.GetUsuarioLogadoCmd;
import br.com.crux.entity.CategoriasContabeis;
import br.com.crux.entity.ItensMovimentacoes;
import br.com.crux.entity.Material;
import br.com.crux.entity.Movimentacoes;
import br.com.crux.to.ItensMovimentacoesTO;

@Component
public class ItensMovimentacoesTOBuilder {

	@Autowired CategoriasContabeisTOBuilder categoriasContabeisTOBuilder;
	@Autowired DepartamentoTOBuilder departamentoTOBuilder;
	@Autowired MaterialTOBuilder materialTOBuilder;
	@Autowired PedidosMateriaisTOBuilder pedidosMateriaisTOBuilder;
	@Autowired UnidadeTOBuilder unidadeTOBuilder;
	@Autowired GetUsuarioLogadoCmd getUsuarioLogadoCmd;
	@Autowired GetCategoriasContabeisCmd getCategoriasContabeisCmd;
	@Autowired GetDepartamentoCmd getDepartamentoCmd;
	@Autowired GetMaterialCmd getMaterialCmd;
	@Autowired GetUnidadeCmd getUnidadeCmd;

	public ItensMovimentacoesTO buildTO(ItensMovimentacoes entity) {
		ItensMovimentacoesTO to = new ItensMovimentacoesTO();

		BeanUtils.copyProperties(entity, to);

		to.setId(entity.getId());

		to.setCategoria(categoriasContabeisTOBuilder.buildTOCombo(entity.getCategoria()));
		to.setDepartamento(departamentoTOBuilder.buildTOCombo(entity.getDepartamento()));
		to.setMaterial(materialTOBuilder.buildTOCombo(entity.getMaterial()));
		to.setPedidosMateriais(pedidosMateriaisTOBuilder.buildTOCombo(entity.getPedidosMateriais()));
		to.setUnidade(unidadeTOBuilder.buildTOCombo(entity.getUnidade()));
		
		return to;
	}

	public List<ItensMovimentacoesTO> buildAll(List<ItensMovimentacoes> lista) {
		return lista.stream()
				.map(this::buildTO)
				.collect(Collectors.toList());
	}

	public ItensMovimentacoes build(Movimentacoes movimentacoes, ItensMovimentacoesTO to) {
		ItensMovimentacoes entity = new ItensMovimentacoes();

		BeanUtils.copyProperties(to, entity);

		if (Objects.nonNull(to.getCategoria()) && Objects.nonNull(to.getCategoria()
				.getId())) {
			CategoriasContabeis retorno = getCategoriasContabeisCmd.getById(to.getCategoria()
					.getId());
			entity.setCategoria(retorno);
		}

		if (Objects.nonNull(to.getMaterial()) && Objects.nonNull(to.getMaterial()
				.getId())) {
			Material retorno = getMaterialCmd.getById(to.getMaterial()
					.getId());
			entity.setMaterial(retorno);
		}

		entity.setUnidade(movimentacoes.getUnidade());
		entity.setDepartamento(movimentacoes.getDepartamento());
		entity.setMovimentacao(movimentacoes);
		entity.setUsuarioAlteracao(getUsuarioLogadoCmd.getUsuarioLogado().getIdUsuario());
		
		return entity;
	}
	
	public ItensMovimentacoesTO buildTOCombo(ItensMovimentacoes entity) {
		ItensMovimentacoesTO to = new ItensMovimentacoesTO();

		BeanUtils.copyProperties(entity, to);

		to.setId(entity.getId());

		return to;
	}

	public List<ItensMovimentacoesTO> buildAllCombo(List<ItensMovimentacoes> lista) {
		return lista.stream()
				.map(this::buildTOCombo)
				.collect(Collectors.toList());
	}


}
