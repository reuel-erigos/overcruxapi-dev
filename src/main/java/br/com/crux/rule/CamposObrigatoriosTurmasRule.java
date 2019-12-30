package br.com.crux.rule;

import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import br.com.crux.exception.CamposObrigatoriosException;
import br.com.crux.to.TurmasTO;

@Component
public class CamposObrigatoriosTurmasRule {

	public void verificar(TurmasTO to) {
		if (StringUtils.isEmpty(to.getDescricao())) {
			throw new CamposObrigatoriosException("A descrição deve ser informada.");
		}

		if (Objects.isNull(to.getDataPrevisaoInicio())) {
			throw new CamposObrigatoriosException("Data de Início de Previsão deve ser informada.");
		}

		if (Objects.isNull(to.getUnidade())) {
			throw new CamposObrigatoriosException("Unidade deve ser informada.");
		}
		
	}
}
