package br.com.crux.cmd;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.dao.repository.UsuariosUnidadeRepository;
import br.com.crux.entity.UsuariosSistema;
import br.com.crux.exception.ParametroNaoInformadoException;

@Component
public class ExcluirUsuarioUnidadeCmd {

	@Autowired private UsuariosUnidadeRepository repository;
	@Autowired private GetUsuarioSistemaCmd getUsuarioSistemaCmd;

	public void excluirPorUsuario(Long idUsuario) {
		Optional.ofNullable(idUsuario).orElseThrow(() -> new ParametroNaoInformadoException("Usuário não informado."));
		
		UsuariosSistema usuariosSistema = getUsuarioSistemaCmd.getById(idUsuario);
		
		repository.deleteByUsuarioSistema(usuariosSistema);
	}
}
