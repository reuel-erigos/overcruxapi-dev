package br.com.crux.rule;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import br.com.crux.exception.CamposObrigatoriosException;
import br.com.crux.to.MotivoDesligamentoTO;

@Component
public class CamposObrigatoriosMotivoDesligamentoRule {

	public void verificar(MotivoDesligamentoTO to) {

		if (StringUtils.isEmpty(to.getMotivoDesligamento())) {
			throw new CamposObrigatoriosException(
					"Operação não realizada. O campo motivo do desligamento deve ser informado.");
		}

	}
}
