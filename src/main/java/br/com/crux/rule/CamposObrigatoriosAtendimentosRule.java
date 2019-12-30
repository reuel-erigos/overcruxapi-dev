package br.com.crux.rule;

import java.util.Objects;

import org.springframework.stereotype.Component;

import br.com.crux.exception.CamposObrigatoriosException;
import br.com.crux.to.AtendimentosTO;

@Component
public class CamposObrigatoriosAtendimentosRule {

	public void verificar(AtendimentosTO to) {

		if (Objects.isNull(to.getDataAtendimento())) {
			throw new CamposObrigatoriosException("Data de atendimento deve ser informada.");
		}

		if (Objects.isNull(to.getAluno()) || Objects.isNull(to.getAluno())) {
			throw new CamposObrigatoriosException("Aluno deve ser informado.");
		}

		if (Objects.isNull(to.getDiagnostico()) || Objects.isNull(to.getDiagnostico().getId())) {
			throw new CamposObrigatoriosException("Diagnóstico deve ser informado.");
		}

	}
}
