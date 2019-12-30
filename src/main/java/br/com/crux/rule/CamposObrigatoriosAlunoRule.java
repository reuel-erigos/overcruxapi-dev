package br.com.crux.rule;

import java.util.Objects;

import org.springframework.stereotype.Component;

import br.com.crux.exception.CamposObrigatoriosException;
import br.com.crux.to.AlunoTO;

@Component
public class CamposObrigatoriosAlunoRule {

	public void verificar(AlunoTO to) {
		if (Objects.isNull(to.getUnidade()) && Objects.isNull(to.getUnidade().getIdUnidade())) {
			throw new CamposObrigatoriosException("Unidade deve ser informada.");
		}

		if(Objects.isNull(to.getPessoaFisica()) && Objects.isNull(to.getPessoaFisica().getId())) {
			throw new CamposObrigatoriosException("Pessoa Fícisa deve ser informada.");
		}		
	}
}
