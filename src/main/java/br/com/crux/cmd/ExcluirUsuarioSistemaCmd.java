package br.com.crux.cmd;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import br.com.crux.dao.repository.UsuarioSistemaRepository;
import br.com.crux.exception.ParametroNaoInformadoException;
import br.com.crux.exception.TabaleReferenciaEncontradaException;

@Component
public class ExcluirUsuarioSistemaCmd {

	@Autowired private UsuarioSistemaRepository repository;
	@Autowired private ExcluirUsuarioUnidadeCmd excluirUsuarioUnidadeCmd;

	public void excluir(Long idUsuario) {
		
		try {
			Optional.ofNullable(idUsuario).orElseThrow(() -> new ParametroNaoInformadoException("Usuário não informado."));
			
			excluirUsuarioUnidadeCmd.excluirPorUsuario(idUsuario);
			
			repository.deleteById(idUsuario);
			
		} catch (DataIntegrityViolationException e) {
			throw new TabaleReferenciaEncontradaException("Erro ao excluir, verifique se há outro cadastro com referência a este usuário.");
		}	
		
	}
}
