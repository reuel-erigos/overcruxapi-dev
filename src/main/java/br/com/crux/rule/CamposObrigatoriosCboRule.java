package br.com.crux.rule;

import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import br.com.crux.exception.CamposObrigatoriosException;
import br.com.crux.to.CboTO;

@Component
public class CamposObrigatoriosCboRule {

	public void verificar(CboTO to) {
		if (StringUtils.isEmpty(to.getNomeTitulo())) {
			throw new CamposObrigatoriosException("O nome deve ser informado.");
		}

		if (StringUtils.isEmpty(to.getDescricaoTipo())) {
			throw new CamposObrigatoriosException("Descrição do tipo deve ser informada.");
		}
		
		if (Objects.isNull(to.getNumero())) {
			throw new CamposObrigatoriosException("Número deve ser informado.");
		}

	}
}
