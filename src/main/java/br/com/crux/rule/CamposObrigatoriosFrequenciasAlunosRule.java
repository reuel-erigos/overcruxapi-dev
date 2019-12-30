package br.com.crux.rule;

import java.util.Objects;

import org.springframework.stereotype.Component;

import br.com.crux.exception.CamposObrigatoriosException;
import br.com.crux.to.FrequenciasAlunosTO;

@Component
public class CamposObrigatoriosFrequenciasAlunosRule {

	public void verificar(FrequenciasAlunosTO to) {
		if(Objects.isNull(to.getAtividadesAluno())) {
			throw new CamposObrigatoriosException("Atividade do aluno deve ser informada.");
		}
		
	}
}
