package br.com.crux.rule;

import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import br.com.crux.exception.CamposObrigatoriosException;
import br.com.crux.to.TiposContratacoesTO;

@Component
public class CamposObrigatoriosTiposContratacoesRule {

	public void verificar(TiposContratacoesTO to) {
		if (StringUtils.isEmpty(to.getCodigoTipoContratacao())) {
			throw new CamposObrigatoriosException("O código deve ser informado.");
		}

		if (Objects.isNull(to.getDescricaoTipoContratacao())) {
			throw new CamposObrigatoriosException("A descrição deve ser informada.");
		}

	}
}
