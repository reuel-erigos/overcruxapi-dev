package br.com.crux.builder;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import br.com.crux.entity.Diagnosticos;
import br.com.crux.to.DiagnosticosTO;

@Component
public class DiagnosticosTOBuilder {

	public Diagnosticos build(DiagnosticosTO p) {
		Diagnosticos retorno = new Diagnosticos();

		retorno.setId(p.getId());
		retorno.setDescricao(p.getDescricao());
		retorno.setUsuarioAlteracao(p.getUsuarioAlteracao());

		return retorno;
	}

	public DiagnosticosTO buildTO(Diagnosticos p) {
		DiagnosticosTO retorno = new DiagnosticosTO();
		
		retorno.setId(p.getId());
		retorno.setDescricao(p.getDescricao());
		retorno.setUsuarioAlteracao(p.getUsuarioAlteracao());

		return retorno;
	}

	public List<DiagnosticosTO> buildAll(List<Diagnosticos> dtos) {
		return dtos.stream().map(dto -> buildTO(dto)).collect(Collectors.toList());
	}

}
