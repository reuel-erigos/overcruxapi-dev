package br.com.crux.cmd;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.MenuTOBuilder;
import br.com.crux.dao.MenuDao;
import br.com.crux.dao.dto.MenuDTO;
import br.com.crux.rule.VerificaParametrosAcessoRule;
import br.com.crux.rule.VerificaPermissaoMenuRule;
import br.com.crux.to.MenuTO;
import br.com.crux.to.UsuarioLogadoTO;

@Component
public class GetMenuCmd {
	
	@Autowired private MenuDao menuDao ;
	@Autowired private MenuTOBuilder menuTOBuilder;
	@Autowired private VerificaParametrosAcessoRule verificaParametrosAcessoRule;
	@Autowired private VerificaPermissaoMenuRule verificaPermissaoMenuRule;
	@Autowired private GetUsuarioLogadoCmd getUsuarioLogadoCmd;
	@Autowired private GetUnidadeLogadaCmd getUnidadeLogadaCmd;
	
	
	public List<MenuTO> getMenuPrincipal() {
		Long idUnidade = getUnidadeLogadaCmd.get().getId();
		
		UsuarioLogadoTO usuarioLogado = getUsuarioLogadoCmd.getUsuarioLogado();
		String username = usuarioLogado.getUsername();
		
		verificaParametrosAcessoRule.verificar(username, idUnidade);
		List<MenuDTO> menu = menuDao.getMenuPrincipal(usuarioLogado.getIdUsuario(), idUnidade);
		
		verificaPermissaoMenuRule.verificar(menu);
		
		return menuTOBuilder.buildAll(menu);
	}

}
