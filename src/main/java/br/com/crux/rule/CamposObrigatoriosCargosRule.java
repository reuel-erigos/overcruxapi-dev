package br.com.crux.rule;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import br.com.crux.exception.CamposObrigatoriosException;
import br.com.crux.to.CargoTO;

@Component
public class CamposObrigatoriosCargosRule {

	public void verificar(CargoTO to) {
		
		if (StringUtils.isEmpty(to.getCodigo())) {
			throw new CamposObrigatoriosException("Código deve ser informado.");
		}

		if (StringUtils.isEmpty(to.getTipoCargo())) {
			throw new CamposObrigatoriosException("Tipo do Cargo deve ser informada.");
		}

	}

}
