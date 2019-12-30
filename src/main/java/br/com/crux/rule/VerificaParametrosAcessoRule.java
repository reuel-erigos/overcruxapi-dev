package br.com.crux.rule;

import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import br.com.crux.exception.ParametroNaoInformadoException;

@Component
public class VerificaParametrosAcessoRule {

	public void verificar(String username, Long idUnidade) {
		if (StringUtils.isEmpty(username)) {
			throw new ParametroNaoInformadoException("Parâmetro username não informado");
		}
		if (Objects.isNull(idUnidade)) {
			throw new ParametroNaoInformadoException("Parâmetro unidade não informado");
		}
		
	}

}
