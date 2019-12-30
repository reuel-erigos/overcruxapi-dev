package br.com.crux.rule;

import java.util.Objects;

import org.springframework.stereotype.Component;

import br.com.crux.exception.CamposObrigatoriosException;
import br.com.crux.to.ReprovacoesAlunoTO;

@Component
public class CamposObrigatoriosReprovacoesAlunoRule {

	public void verificar(ReprovacoesAlunoTO to) {
		if(Objects.isNull(to.getAluno())) {
			throw new CamposObrigatoriosException("Aluno deve ser informado.");
		}
		
	}
}
