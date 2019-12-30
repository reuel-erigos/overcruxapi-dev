package br.com.crux.rule;

import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import br.com.crux.exception.CamposObrigatoriosException;
import br.com.crux.to.PlanosAcaoTO;

@Component
public class CamposObrigatoriosPlanosAcaoRule {

	public void verificar(PlanosAcaoTO to) {
		if (StringUtils.isEmpty(to.getNome())) {
			throw new CamposObrigatoriosException("O nome do departamento deve ser informado.");
		}

		if (Objects.isNull(to.getDataInicio())) {
			throw new CamposObrigatoriosException("Data de Início deve ser informada.");
		}

		if (Objects.isNull(to.getIniciativa())) {
			throw new CamposObrigatoriosException("Iniciativa deve ser informada.");
		}

	}
}
