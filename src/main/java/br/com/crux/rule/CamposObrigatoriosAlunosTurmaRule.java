package br.com.crux.rule;

import java.util.Objects;

import org.springframework.stereotype.Component;

import br.com.crux.exception.CamposObrigatoriosException;
import br.com.crux.to.AlunosTurmaTO;

@Component
public class CamposObrigatoriosAlunosTurmaRule {

	
	public void verificar(AlunosTurmaTO to) {
		if(Objects.isNull(to.getAluno())) {
			throw new CamposObrigatoriosException("Aluno deve ser informado.");
		}
		if(Objects.isNull(to.getTurma())) {
			throw new CamposObrigatoriosException("Turma deve ser informada.");
		}
	}

}
