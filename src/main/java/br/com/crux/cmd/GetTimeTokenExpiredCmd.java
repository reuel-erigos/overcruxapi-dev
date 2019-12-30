package br.com.crux.cmd;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.to.ParametrosTO;

@Component
public class GetTimeTokenExpiredCmd {

	@Autowired
	private GetParametrosCmd getParametrosCmd;
	

	public Integer getTimeExpieredToken() {
		ParametrosTO param = getParametrosCmd.getByCodigo("EXPIRED_JWT_TIME");
		if( Objects.nonNull(param.getValor()) ) {
			return Integer.valueOf(param.getValor());
		}
		return null;
	}
	
}
