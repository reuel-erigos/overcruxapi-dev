package br.com.crux.rule;

import java.util.Objects;

import org.springframework.stereotype.Component;

import br.com.crux.exception.CamposObrigatoriosException;
import br.com.crux.to.MateriaisProjetoTO;

@Component
public class CamposObrigatoriosMateriaisProjetoRule {

	public void verificar(MateriaisProjetoTO to) {
		if (Objects.isNull(to.getMaterial())) {
			throw new CamposObrigatoriosException("O material deve ser informado.");
		}

	}
}
