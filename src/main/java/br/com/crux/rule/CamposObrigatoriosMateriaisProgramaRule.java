package br.com.crux.rule;

import java.util.Objects;

import org.springframework.stereotype.Component;

import br.com.crux.exception.CamposObrigatoriosException;
import br.com.crux.to.MateriaisProgramaTO;

@Component
public class CamposObrigatoriosMateriaisProgramaRule {

	public void verificar(MateriaisProgramaTO to) {
		if (Objects.isNull(to.getMaterial())) {
			throw new CamposObrigatoriosException("O material deve ser informado.");
		}

	}
}
