package br.com.crux.rule;

import java.util.Objects;

import org.springframework.stereotype.Component;

import br.com.crux.exception.CamposObrigatoriosException;
import br.com.crux.to.AlunosTrabalhandoTO;

@Component
public class CamposObrigatoriosAlunosTrabalhandoRule {

	public void verificar(AlunosTrabalhandoTO to) {
		if(Objects.isNull(to.getAluno()) || Objects.isNull(to.getAluno().getId())) {
			throw new CamposObrigatoriosException("Aluno deve ser informado.");
		}
		
	}
}
