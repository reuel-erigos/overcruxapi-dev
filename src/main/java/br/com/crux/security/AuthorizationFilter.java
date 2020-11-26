package br.com.crux.security;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.crux.exception.TokenInvalidoException;
import br.com.crux.infra.constantes.SecurityContantes;
import br.com.crux.service.exception.ApiError;
import io.jsonwebtoken.Claims;

@Order(Ordered.LOWEST_PRECEDENCE)
public class AuthorizationFilter extends OncePerRequestFilter {
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String jwt              = request.getHeader(HttpHeaders.AUTHORIZATION);
		String idsessionusuario = request.getHeader(SecurityContantes.IDSESSIONUSUARIO);
		
		try {
			if((jwt == null || !jwt.startsWith(SecurityContantes.JWT_PROVIDER)) && StringUtils.isNotEmpty(idsessionusuario) ) {
				
				throw new TokenInvalidoException("Token ausente na requisição.");
				
			} else {
				if(StringUtils.isNotEmpty(jwt)) {
					jwt = jwt.replace(SecurityContantes.JWT_PROVIDER, "");
					
					//Valida o token informado
					Claims claims = new JwtManager().validaToken(jwt);
					if( claims == null ) {
						throw new TokenInvalidoException("Token de acesso inválido.");
					}
					
					String username = claims.getSubject();
					
					@SuppressWarnings("unchecked")
					List<String> roles = (List<String>) claims.get(SecurityContantes.JWT_ROLE_KEY);
					
					List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
					roles.forEach(role -> {
						grantedAuthorities.add(new SimpleGrantedAuthority(role));
					});
					
					Authentication authentication = new UsernamePasswordAuthenticationToken(username +"@"+idsessionusuario, null, grantedAuthorities);
					SecurityContextHolder.getContext().setAuthentication(authentication);
				}
			}
			
		} catch (Exception e) {
			ApiError apiError = new ApiError(HttpStatus.UNAUTHORIZED.value(), SecurityContantes.JWT_INVALID_MSG);
			PrintWriter writer = response.getWriter();
			
			ObjectMapper mapper = new ObjectMapper();
			String apiErrorString = mapper.writeValueAsString(apiError);
			
			writer.write(apiErrorString);
			
			response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
			response.setStatus(HttpStatus.UNAUTHORIZED.value());
			
			return;
		}
		
		filterChain.doFilter(request, response);
		
	}


}
