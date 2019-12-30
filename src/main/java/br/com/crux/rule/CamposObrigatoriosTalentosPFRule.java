package br.com.crux.rule;

import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import br.com.crux.exception.CamposObrigatoriosException;
import br.com.crux.to.TalentosPfTO;

@Component
public class CamposObrigatoriosTalentosPFRule {


	public void verificar(TalentosPfTO to) {

		if(Objects.isNull(to.getFuncionario()) || Objects.isNull(to.getFuncionario().getId())) {
			throw new CamposObrigatoriosException("Funcionário deve ser informado.");
		}
		
		if(StringUtils.isEmpty(to.getRespostaTalento())) {
			throw new CamposObrigatoriosException("Resposta do Talento deve ser informado.");
		}

		if(Objects.isNull(to.getQuestionario()) || Objects.isNull(to.getQuestionario().getId())) {
			throw new CamposObrigatoriosException("Questionário deve ser informado.");
		}
	}
}
