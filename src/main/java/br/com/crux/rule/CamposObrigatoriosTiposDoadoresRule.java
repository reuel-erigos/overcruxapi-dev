package br.com.crux.rule;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import br.com.crux.exception.CamposObrigatoriosException;
import br.com.crux.to.TiposDoadoresTO;

@Component
public class CamposObrigatoriosTiposDoadoresRule {

	public void verificar(TiposDoadoresTO to) {
		if(StringUtils.isEmpty(to.getCodigo())) {
			throw new CamposObrigatoriosException("Operação não realizada. O campo código deve ser informado.");
		}

	}
}
