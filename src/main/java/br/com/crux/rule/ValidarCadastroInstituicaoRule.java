package br.com.crux.rule;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import br.com.crux.exception.ParametroNaoInformadoException;
import br.com.crux.to.InstituicaoTO;

@Component
public class ValidarCadastroInstituicaoRule {
	
	
	public void validar(InstituicaoTO to) {
		if(StringUtils.isEmpty(to.getNome())) {
			throw new ParametroNaoInformadoException("O nome da instituição não foi informada.");
		}
	}

}
