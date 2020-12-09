package br.com.crux.rule;

import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import br.com.crux.exception.CamposObrigatoriosException;
import br.com.crux.to.OficinasTO;

@Component
public class CamposObrigatoriosAtividadeRule {

	public void verificar(OficinasTO to) {

		if (StringUtils.isEmpty(to.getDescricao())) {
			throw new CamposObrigatoriosException("Descrição deve ser informada.");
		}

		if (Objects.isNull(to.getUnidade()) && Objects.isNull(to.getUnidade().getIdUnidade())) {
			throw new CamposObrigatoriosException("Unidade deve ser informada.");
		}

	}
}
