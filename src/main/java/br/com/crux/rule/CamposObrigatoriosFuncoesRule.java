package br.com.crux.rule;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import br.com.crux.exception.CamposObrigatoriosException;
import br.com.crux.to.FuncoesTO;

@Component
public class CamposObrigatoriosFuncoesRule {

	public void verificar(FuncoesTO to) {
		if (StringUtils.isEmpty(to.getNome())) {
			throw new CamposObrigatoriosException("O nome deve ser informado.");
		}
		
	}
}
