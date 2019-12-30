package br.com.crux.rule;

import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import br.com.crux.exception.CamposObrigatoriosException;
import br.com.crux.to.AcaoTO;

@Component
public class CamposObrigatoriosAcaoRule {

	public void verificar(AcaoTO to) {
		if (Objects.isNull(to.getDataInicio())) {
			throw new CamposObrigatoriosException("Data de Início deve ser informada.");
		}

		if (StringUtils.isEmpty(to.getNome())) {
			throw new CamposObrigatoriosException("Nome deve ser informado.");
		}

		if (Objects.isNull(to.getAtividade()) && Objects.isNull(to.getAtividade().getId())) {
			throw new CamposObrigatoriosException("Atividade deve ser informada.");
		}

	}
}
