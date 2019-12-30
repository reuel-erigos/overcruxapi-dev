package br.com.crux.rule;

import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Component;

import br.com.crux.dao.dto.MenuDTO;
import br.com.crux.exception.UsuarioSemAcessoException;

@Component
public class VerificaPermissaoMenuRule {

	public void verificar(List<MenuDTO> menu) {
		if(Objects.isNull(menu) || menu.isEmpty()) {
			throw new UsuarioSemAcessoException("O usuário não possui acesso a esta unidade");
		}
		
	}

}
