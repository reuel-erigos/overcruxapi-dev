package br.com.crux.builder;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import br.com.crux.entity.Questionario;
import br.com.crux.enums.TipoQuestionario;
import br.com.crux.to.QuestionarioTO;

@Component
public class QuestionariosTOBuilder {

	public Questionario build(QuestionarioTO p) {
		Questionario retorno = new Questionario();

		retorno.setId(p.getId());
		retorno.setDescricao(p.getDescricao());
		retorno.setDataInicio(p.getDataInicio());
		retorno.setDataFim(p.getDataFim());
		
		Optional.ofNullable(p.getTipoQuestionario()).ifPresent(tq -> {
			retorno.setTipoQuestionario(TipoQuestionario.getPorTipo(tq));
		});
		
		
		
		retorno.setUsuarioAlteracao(p.getUsuarioAlteracao());

		return retorno;
	}

	public QuestionarioTO buildTO(Questionario p) {
		QuestionarioTO retorno = new QuestionarioTO();
		
		retorno.setId(p.getId());
		retorno.setDescricao(p.getDescricao());
		retorno.setDataInicio(p.getDataInicio());
		retorno.setDataFim(p.getDataFim());
		
		Optional.ofNullable(p.getTipoQuestionario()).ifPresent(tq -> {
			retorno.setTipoQuestionario(p.getTipoQuestionario().getTipo());
		});
		
		
		
		retorno.setUsuarioAlteracao(p.getUsuarioAlteracao());

		return retorno;
	}

	public List<QuestionarioTO> buildAll(List<Questionario> dtos) {
		return dtos.stream().map(dto -> buildTO(dto)).collect(Collectors.toList());
	}

}
