package br.com.crux.rule;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import br.com.crux.exception.ParametroNaoInformadoException;

@Component
public class ValidarCadastroUnidadeRule {
	
	
	public void validar(String sigla, String nome) {
		if(StringUtils.isEmpty(sigla)) {
			throw new ParametroNaoInformadoException("A sigla da unidade não foi informada.");
		}
		if(StringUtils.isEmpty(nome)) {
			throw new ParametroNaoInformadoException("O nomeda da unidade não foi informada.");
		}
	}

}
