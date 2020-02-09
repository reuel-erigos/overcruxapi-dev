package br.com.crux.rule;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import br.com.crux.exception.CamposObrigatoriosException;
import br.com.crux.to.CategoriasContabeisTO;

@Component
public class CamposObrigatoriosCategoriasContabeisRule {

	public void verificar(CategoriasContabeisTO to) {
		if (StringUtils.isEmpty(to.getNome())) {
			throw new CamposObrigatoriosException("O nome deve ser informado.");
		}

		if (StringUtils.isEmpty(to.getTipo())) {
			throw new CamposObrigatoriosException("O tipo deve ser informado.");
		}

	}
}
