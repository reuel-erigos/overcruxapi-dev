package br.com.crux.builder;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.cmd.GetCategoriasContabeisCmd;
import br.com.crux.cmd.GetRateiosCategoriasMovimentosCmd;
import br.com.crux.cmd.GetUsuarioLogadoCmd;
import br.com.crux.entity.CategoriasContabeis;
import br.com.crux.entity.CategoriasMovimentos;
import br.com.crux.entity.Movimentacoes;
import br.com.crux.to.CategoriasMovimentosTO;
import br.com.crux.to.RateiosCategoriasMovimentosTO;

@Component
public class CategoriasMovimentosTOBuilder {

	@Autowired private CategoriasContabeisTOBuilder categoriasContabeisTOBuilder;
	@Autowired private GetUsuarioLogadoCmd getUsuarioLogadoCmd;
	@Autowired private GetCategoriasContabeisCmd getCategoriasContabeisCmd;
	@Autowired private GetRateiosCategoriasMovimentosCmd getRateiosCmd;
	
	public CategoriasMovimentosTO buildTO(CategoriasMovimentos entity) {
		CategoriasMovimentosTO to = new CategoriasMovimentosTO();

		BeanUtils.copyProperties(entity, to);
		
		to.setCategoriaOrigem(categoriasContabeisTOBuilder.buildTOCombo(entity.getCategoriaOrigem()));
		to.setCategoriaDestino(categoriasContabeisTOBuilder.buildTOCombo(entity.getCategoriaDestino()));
		
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
		
		entity.setUsuarioAlteracao(getUsuarioLogadoCmd.getUsuarioLogado().getIdUsuario());

		return entity;
	}

}
