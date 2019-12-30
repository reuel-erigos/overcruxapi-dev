package br.com.crux.security;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import br.com.crux.cmd.CarregarUnidadeLogadaCmd;
import br.com.crux.cmd.GetUsuarioLogadoCmd;
import br.com.crux.cmd.SaveUsuarioLogadoCmd;
import br.com.crux.to.AcessoUnidadeTO;
import br.com.crux.to.UsuarioLogadoTO;

@Component
public class UnidadeLogadaFilter implements Filter {

	@Autowired private CarregarUnidadeLogadaCmd carregarUnidadeLogadaCmd;
	@Autowired private GetUsuarioLogadoCmd getUsuarioLogadoCmd;
	@Autowired private SaveUsuarioLogadoCmd saveUsuarioLogadoCmd;
	
	public UnidadeLogadaFilter() {
    }

	public void destroy() {}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req  = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		
		if(req.getRequestURI().contains("/unidade/logada/")) {
			String unidadeLogada = req.getRequestURI().replaceAll("/unidade/logada/", "");
			
			Authentication auth = getUsuarioLogadoCmd.getAuthentication();
			UsuarioLogadoTO usuarioLogado = saveUsuarioLogadoCmd.save(auth);
			AcessoUnidadeTO unidadeLogadaTO = carregarUnidadeLogadaCmd.carregarUnidadeLogada(Long.valueOf(unidadeLogada));
			usuarioLogado.setUnidadeLogada(unidadeLogadaTO);
			
			UsuarioLocals.set(auth.getName(), usuarioLogado);
		}
		
		chain.doFilter(request, res);
	}

	public void init(FilterConfig filterConfig) throws ServletException {
		//WebApplicationContext appContext = WebApplicationContextUtils.getRequiredWebApplicationContext(filterConfig.getServletContext());
		//this.usuarioLogadoHolder = appContext.getBean(UsuarioLogadoHolder.class);
	}

}
