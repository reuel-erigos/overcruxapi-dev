package br.com.crux.builder;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import br.com.crux.entity.Avaliacoes;
import br.com.crux.to.AvaliacoesTO;

@Component
public class AvaliacoesTOBuilder {

	public Avaliacoes build(AvaliacoesTO p) {
		Avaliacoes retorno = new Avaliacoes();

		retorno.setId(p.getId());
		retorno.setNome(p.getNome());
		retorno.setDescricao(p.getDescricao());
		retorno.setDataInicio(p.getDataInicio());
		retorno.setDataFim(p.getDataFim());
		retorno.setUsuarioAlteracao(p.getUsuarioAlteracao());

		return retorno;
	}

	public AvaliacoesTO buildTO(Avaliacoes p) {
		AvaliacoesTO retorno = new AvaliacoesTO();
		
		if(Objects.isNull(p)) {
			return retorno;
		}
		
		retorno.setId(p.getId());
		retorno.setNome(p.getNome());
		retorno.setDescricao(p.getDescricao());
		retorno.setDataInicio(p.getDataInicio());
		retorno.setDataFim(p.getDataFim());
		retorno.setUsuarioAlteracao(p.getUsuarioAlteracao());

		return retorno;
	}

	public List<AvaliacoesTO> buildAll(List<Avaliacoes> dtos) {
		return dtos.stream().map(dto -> buildTO(dto)).collect(Collectors.toList());
	}

}
