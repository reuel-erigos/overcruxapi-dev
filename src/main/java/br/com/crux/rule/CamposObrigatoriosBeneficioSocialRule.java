package br.com.crux.rule;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import br.com.crux.exception.CamposObrigatoriosException;

@Component
public class CamposObrigatoriosBeneficioSocialRule {

	public void verificar(String descricao) {
		
		if(StringUtils.isEmpty(descricao)) {
			throw new CamposObrigatoriosException("O campo descrição deve ser informado.");
		}
		
	}
}
