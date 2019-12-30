package br.com.crux.cmd;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import br.com.crux.dao.repository.UsuariosUnidadeRepository;
import br.com.crux.entity.UsuariosSistema;
import br.com.crux.exception.ParametroNaoInformadoException;
import br.com.crux.exception.TabaleReferenciaEncontradaException;

@Component
public class ExcluirUsuarioUnidadeCmd {

	@Autowired private UsuariosUnidadeRepository repository;
	@Autowired private GetUsuarioSistemaCmd getUsuarioSistemaCmd;

	public void excluirPorUsuario(Long idUsuario) {
		
		try {
			Optional.ofNullable(idUsuario).orElseThrow(() -> new ParametroNaoInformadoException("Usuário não informado."));
			
			UsuariosSistema usuariosSistema = getUsuarioSistemaCmd.getById(idUsuario);
			
			repository.deleteByUsuarioSistema(usuariosSistema);
			
		} catch (DataIntegrityViolationException e) {
			throw new TabaleReferenciaEncontradaException("Erro ao excluir, verifique se há outro cadastro com referência a este usuário unidade.");
		}	
		
	}
}
