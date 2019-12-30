package br.com.crux.rule;

import java.util.Objects;

import org.springframework.stereotype.Component;

import br.com.crux.exception.CamposObrigatoriosException;
import br.com.crux.to.AtividadesAlunoTO;

@Component
public class CamposObrigatoriosAtividadesAlunoRule {

	
	public void verificar(AtividadesAlunoTO to) {
		if(Objects.isNull(to.getAtividade()) || Objects.isNull(to.getAtividade().getId())) {
			throw new CamposObrigatoriosException("Atividade deve ser informada.");
		}
		if(Objects.isNull(to.getAluno()) || Objects.isNull(to.getAluno().getId())) {
			throw new CamposObrigatoriosException("Aluno deve ser informado.");
		}
		
		
	}
}
