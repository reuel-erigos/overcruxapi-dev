package br.com.crux.rule;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import br.com.crux.exception.CamposObrigatoriosException;

@Component
public class CamposObrigatoriosDiagnosticosRule {

	public void verificar(String descricao) {
		if(StringUtils.isEmpty(descricao)) {
			throw new CamposObrigatoriosException("Descricao deve ser informado.");
		}
		
	}
}
