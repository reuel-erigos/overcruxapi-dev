package br.com.crux.rule;

import java.util.Objects;

import org.springframework.stereotype.Component;

import br.com.crux.exception.CamposObrigatoriosException;

@Component
public class ValidarProvisionamentoRule {

	public void verificar(Long dataInicio, Long dataFim ) {
		if(Objects.isNull(dataInicio)) {
			throw new CamposObrigatoriosException("A data de início deve ser informada.");
		}
		if(Objects.isNull(dataFim)) {
			throw new CamposObrigatoriosException("A data fim deve ser informada.");
		}
	}
}
