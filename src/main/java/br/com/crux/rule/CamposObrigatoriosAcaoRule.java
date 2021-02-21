package br.com.crux.rule;

import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import br.com.crux.exception.CamposObrigatoriosException;
import br.com.crux.to.AcaoTO;

@Component
public class CamposObrigatoriosAcaoRule {

	public void verificar(AcaoTO to) {
		if (Objects.isNull(to.getDataPrevisaoInicio())) {
			throw new CamposObrigatoriosException("Data Previsão de Início deve ser informada.");
		}

		if (StringUtils.isEmpty(to.getDescricao())) {
			throw new CamposObrigatoriosException("Descrição deve ser informada.");
		}
		
	}
}
