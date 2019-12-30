package br.com.crux.rule;

import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import br.com.crux.exception.CamposObrigatoriosException;
import br.com.crux.to.PerspectivaTO;

@Component
public class CamposObrigatoriosPerspectivaRule {

	public void verificar(PerspectivaTO to) {
		if (StringUtils.isEmpty(to.getNmPerspectiva())) {
			throw new CamposObrigatoriosException("O nome do departamento deve ser informado.");
		}

		if (Objects.isNull(to.getDtImplantacao())) {
			throw new CamposObrigatoriosException("Data de implantação deve ser informada.");
		}

		if (Objects.isNull(to.getUnidade())) {
			throw new CamposObrigatoriosException("Unidade deve ser informada.");
		}

	}
}
