package br.com.crux.cmd;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.dao.repository.ColaboradoresProjetoRepository;
import br.com.crux.exception.ParametroNaoInformadoException;

@Component
public class ExcluirColaboradoresProjetoCmd {

	@Autowired private ColaboradoresProjetoRepository repository;

	public void excluir(Long id) {
		Long idColaborador = Optional.ofNullable(id).orElseThrow(() -> new ParametroNaoInformadoException("Erro ao excluir, parâmetro ausente."));
		
		repository.deleteById(idColaborador);
	}
}
