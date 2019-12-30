package br.com.crux.cmd;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import br.com.crux.dao.repository.ColaboradoresProgramaRepository;
import br.com.crux.exception.ParametroNaoInformadoException;
import br.com.crux.exception.TabaleReferenciaEncontradaException;

@Component
public class ExcluirColaboradoresProgramaCmd {

	@Autowired private ColaboradoresProgramaRepository repository;

	public void excluir(Long id) {

		try {
			Long idColaborador = Optional.ofNullable(id).orElseThrow(() -> new ParametroNaoInformadoException("Erro ao excluir, parâmetro ausente."));
			repository.deleteById(idColaborador);
		} catch (DataIntegrityViolationException e) {
			throw new TabaleReferenciaEncontradaException("Erro ao excluir, verifique se há outro cadastro com referência a este colaborador.");
		}	
		
	}
}
