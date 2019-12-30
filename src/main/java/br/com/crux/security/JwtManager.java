package br.com.crux.security;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.cmd.GetTimeTokenExpiredCmd;
import br.com.crux.infra.constantes.SecurityContantes;
import br.com.crux.infra.util.Java8DateUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtManager {
	
	@Autowired
	private GetTimeTokenExpiredCmd getTimeTokenExpiredCmd;

	public String createToken(String username, List<String> roles) {
		JwtBuilder token = Jwts.builder()
  							   .setSubject(username)
							   .claim(SecurityContantes.JWT_ROLE_KEY, roles)
							   .signWith(SignatureAlgorithm.HS512, SecurityContantes.API_KEY.getBytes());
				 
		Integer minutes = getTimeTokenExpiredCmd.getTimeExpieredToken();
		if( Objects.nonNull(minutes)  && minutes > 0 ) {
			LocalDateTime expired = LocalDateTime.now().plusMinutes(minutes);
			token.setExpiration(Java8DateUtil.getDate(expired));
		}
		
		return token.compact();
	}
	
	public Claims validaToken(String jwt) throws JwtException {
		Claims claims = Jwts.parser()
							.setSigningKey(SecurityContantes.API_KEY.getBytes())
							.parseClaimsJws(jwt)
							.getBody();
		
		
		return claims;
	}
	
}
