package br.com.crux.rule;

import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import br.com.crux.exception.CamposObrigatoriosException;
import br.com.crux.to.IniciativaTO;

@Component
public class CamposObrigatoriosIniciativaRule {

	public void verificar(IniciativaTO to) {
		if (StringUtils.isEmpty(to.getNome())) {
			throw new CamposObrigatoriosException("O nome do departamento deve ser informado.");
		}

		if (Objects.isNull(to.getDataInicio())) {
			throw new CamposObrigatoriosException("Data de Início deve ser informada.");
		}

		if (Objects.isNull(to.getMetas())) {
			throw new CamposObrigatoriosException("Meta deve ser informada.");
		}

	}
}
