package br.com.crux.rule;

import java.util.Objects;

import org.springframework.stereotype.Component;

import br.com.crux.exception.CamposObrigatoriosException;
import br.com.crux.to.ParticipantesAtendimentosTO;

@Component
public class CamposObrigatoriosParticipantesAtendimentosRule {

	public void verificar(ParticipantesAtendimentosTO to) {
		if(Objects.isNull(to.getAtendimento()) || Objects.isNull(to.getAtendimento().getId())) {
			throw new CamposObrigatoriosException("Atendimento deve ser informado.");
		}
		if(Objects.isNull(to.getFuncionario()) || Objects.isNull(to.getFuncionario().getId())) {
			throw new CamposObrigatoriosException("Funcionario deve ser informado.");
		}
		
	}
}
