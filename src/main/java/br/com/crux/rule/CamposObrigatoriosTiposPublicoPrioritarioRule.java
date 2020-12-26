package br.com.crux.rule;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import br.com.crux.exception.CamposObrigatoriosException;
import br.com.crux.to.TiposPublicoPrioritarioTO;

@Component
public class CamposObrigatoriosTiposPublicoPrioritarioRule {

	public void verificar(TiposPublicoPrioritarioTO to) {

		if (StringUtils.isEmpty(to.getDescricao())) {
			throw new CamposObrigatoriosException(
					"Operação não realizada. O campo descrição deve ser informado.");
		}

	}
}
