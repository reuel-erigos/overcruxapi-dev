package br.com.crux.rule;

import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import br.com.crux.entity.Unidade;
import br.com.crux.exception.CamposObrigatoriosException;
import br.com.crux.to.EstruturaUnidadeTO;

@Component
public class CamposObrigatoriosEstruturaUnidadeRule {

	public void verificar(EstruturaUnidadeTO to, Unidade unidade) {
		if(StringUtils.isEmpty(to.getDescricao())) {
			throw new CamposObrigatoriosException("Descricao deve ser informado.");
		}

		if(Objects.isNull(unidade)) {
			throw new CamposObrigatoriosException("Unidade deve ser informada.");
		}
	}
}
