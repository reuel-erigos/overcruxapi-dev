package br.com.crux.cmd;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.to.MenuTO;

@Component
public class GetAcessoUsuarioCmd {

	@Autowired
	private GetMenuCmd getMenuCmd;

	public List<MenuTO> getMenuPrincipal() {
		return getMenuCmd.getMenuPrincipal();
	}

}
