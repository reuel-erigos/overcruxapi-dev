package br.com.crux.rule;

import java.util.Objects;

import org.springframework.stereotype.Component;

import br.com.crux.exception.CamposObrigatoriosException;
import br.com.crux.to.DependentesTO;

@Component
public class CamposObrigatoriosDependentesFuncionarioRule {

	public void verificar(DependentesTO to) {

		if (Objects.isNull(to.getIdFuncionario())) {
			throw new CamposObrigatoriosException("Funcionário deve ser informado.");
		}

		if (Objects.isNull(to.getPessoaFisica())) {
			throw new CamposObrigatoriosException("Dados da pessoa física deve ser informados.");
		}
		
	}
}
