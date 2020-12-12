package br.com.crux.cmd;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.dao.repository.UsuarioSistemaRepository;
import br.com.crux.exception.ParametroNaoInformadoException;

@Component
public class ExcluirUsuarioSistemaCmd {

	@Autowired private UsuarioSistemaRepository repository;
	@Autowired private ExcluirUsuarioUnidadeCmd excluirUsuarioUnidadeCmd;

	public void excluir(Long idUsuario) {
		Optional.ofNullable(idUsuario).orElseThrow(() -> new ParametroNaoInformadoException("Usuário não informado."));
		
		excluirUsuarioUnidadeCmd.excluirPorUsuario(idUsuario);
		
		repository.deleteById(idUsuario);
	}
}
