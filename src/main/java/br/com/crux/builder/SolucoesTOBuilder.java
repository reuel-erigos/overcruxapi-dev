package br.com.crux.builder;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import br.com.crux.entity.Solucoes;
import br.com.crux.to.SolucoesTO;

@Component
public class SolucoesTOBuilder {

	public Solucoes build(SolucoesTO p) {
		Solucoes retorno = new Solucoes();

		retorno.setId(p.getId());
		retorno.setDescricao(p.getDescricao());
		retorno.setUsuarioAlteracao(p.getUsuarioAlteracao());

		return retorno;
	}

	public SolucoesTO buildTO(Solucoes p) {
		SolucoesTO retorno = new SolucoesTO();
		
		retorno.setId(p.getId());
		retorno.setDescricao(p.getDescricao());
		retorno.setUsuarioAlteracao(p.getUsuarioAlteracao());

		return retorno;
	}

	public List<SolucoesTO> buildAll(List<Solucoes> dtos) {
		return dtos.stream().map(dto -> buildTO(dto)).collect(Collectors.toList());
	}

}
