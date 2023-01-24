package br.com.crux.builder;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.cmd.GetCategoriasContabeisCmd;
import br.com.crux.cmd.GetPedidosMateriaisCmd;
import br.com.crux.cmd.GetProgramaCmd;
import br.com.crux.cmd.GetProjetoCmd;
import br.com.crux.cmd.GetRateiosCategoriasMovimentosCmd;
import br.com.crux.cmd.GetUsuarioLogadoCmd;
import br.com.crux.entity.CategoriasContabeis;
import br.com.crux.entity.CategoriasMovimentos;
import br.com.crux.entity.Movimentacoes;
import br.com.crux.entity.PedidosMateriais;
import br.com.crux.entity.Programa;
import br.com.crux.entity.Projeto;
import br.com.crux.to.CategoriasMovimentosTO;
import br.com.crux.to.RateiosCategoriasMovimentosTO;

@Component
public class CategoriasMovimentosTOBuilder {

	@Autowired private CategoriasContabeisTOBuilder categoriasContabeisTOBuilder;
	@Autowired private GetUsuarioLogadoCmd getUsuarioLogadoCmd;
	@Autowired private GetCategoriasContabeisCmd getCategoriasContabeisCmd;
	@Autowired private GetRateiosCategoriasMovimentosCmd getRateiosCmd;
	@Autowired private GetProjetoCmd getProjetoCmd;
	@Autowired private GetProgramaCmd getProgramaCmd;	
	@Autowired private ProgramaTOBuilder programaBuilder;
	@Autowired private ProjetoTOBuilder projetoBuilder;
	@Autowired private PedidosMateriaisTOBuilder pedidosMateriaisBuilder;
	@Autowired private GetPedidosMateriaisCmd getPedidosMateriaisCmd;

	
	public CategoriasMovimentosTO buildTO(CategoriasMovimentos entity) {
		CategoriasMovimentosTO to = new CategoriasMovimentosTO();

		BeanUtils.copyProperties(entity, to);
		
		to.setCategoriaOrigem(categoriasContabeisTOBuilder.buildTOCombo(entity.getCategoriaOrigem()));
		to.setCategoriaDestino(categoriasContabeisTOBuilder.buildTOCombo(entity.getCategoriaDestino()));
		
		if(Objects.nonNull(entity.getPrograma())) {
			to.setPrograma(programaBuilder.buildTO(entity.getPrograma()));
		}
		if(Objects.nonNull(entity.getProjeto())) {
			to.setProjeto(projetoBuilder.buildTO(entity.getProjeto()));
		}
		if(Objects.nonNull(entity.getPedidosMateriais())) {
			to.setPedidosMateriais(pedidosMateriaisBuilder.buildTO(entity.getPedidosMateriais()));
		}
		if(Objects.nonNull(entity.getCategoriaAdicional())) {
			to.setCategoriaAdicional(categoriasContabeisTOBuilder.buildTOCombo(entity.getCategoriaAdicional()));
		}
		
		List<RateiosCategoriasMovimentosTO> rateiosTO = getRateiosCmd.getTOByIdCategoria(entity.getId());
		to.setRateioCategoriasMovimentos(rateiosTO);

		return to;
	}

	public List<CategoriasMovimentosTO> buildAll(List<CategoriasMovimentos> lista) {
		return lista.stream()
				.map(this::buildTO)
				.collect(Collectors.toList());
	}

	public CategoriasMovimentos build(Movimentacoes movimentacoes, CategoriasMovimentosTO to) {
		CategoriasMovimentos entity = new CategoriasMovimentos();

		BeanUtils.copyProperties(to, entity);

		entity.setIdMovimento(movimentacoes.getId());
		
		if (Objects.nonNull(to.getCategoriaOrigem()) && Objects.nonNull(to.getCategoriaOrigem().getId())) {
			CategoriasContabeis retorno = getCategoriasContabeisCmd.getById(to.getCategoriaOrigem().getId());
			entity.setCategoriaOrigem(retorno);
		}

		if (Objects.nonNull(to.getCategoriaDestino()) && Objects.nonNull(to.getCategoriaDestino().getId())) {
			CategoriasContabeis retorno = getCategoriasContabeisCmd.getById(to.getCategoriaDestino().getId());
			entity.setCategoriaDestino(retorno);
		}
		
		if (Objects.nonNull(to.getPrograma()) && Objects.nonNull(to.getPrograma().getId())) {
			Programa registro = getProgramaCmd.getById(to.getPrograma().getId());
			entity.setPrograma(registro);
		}
		if (Objects.nonNull(to.getProjeto()) && Objects.nonNull(to.getProjeto().getId())) {
			Projeto registro = getProjetoCmd.getById(to.getProjeto().getId());
			entity.setProjeto(registro);
		}
		if (Objects.nonNull(to.getPedidosMateriais()) && Objects.nonNull(to.getPedidosMateriais().getId())) {
			PedidosMateriais retorno = getPedidosMateriaisCmd.getById(to.getPedidosMateriais().getId());
			entity.setPedidosMateriais(retorno);
		}
		if (Objects.nonNull(to.getCategoriaAdicional()) && Objects.nonNull(to.getCategoriaAdicional().getId())) {
			CategoriasContabeis retorno = getCategoriasContabeisCmd.getById(to.getCategoriaAdicional().getId());
			entity.setCategoriaAdicional(retorno);
		}

		entity.setUsuarioAlteracao(getUsuarioLogadoCmd.getUsuarioLogado().getIdUsuario());

		return entity;
	}

}
