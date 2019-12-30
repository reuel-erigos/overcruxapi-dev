package br.com.crux.rule;

import java.util.Objects;

import org.springframework.stereotype.Component;

import br.com.crux.exception.CamposObrigatoriosException;
import br.com.crux.to.ColaboradoresProgramaTO;

@Component
public class CamposObrigatoriosColaboradoresProgramaRule {

	public void verificar(ColaboradoresProgramaTO to) {
		if (Objects.isNull(to.getDataInicio())) {
			throw new CamposObrigatoriosException("Data de Início deve ser informada.");
		}

		if (Objects.isNull(to.getFuncionario())) {
			throw new CamposObrigatoriosException("Funcionário deve ser informado.");
		}

		if (Objects.isNull(to.getCargo())) {
			throw new CamposObrigatoriosException("Cargo deve ser informado.");
		}

	}
}
