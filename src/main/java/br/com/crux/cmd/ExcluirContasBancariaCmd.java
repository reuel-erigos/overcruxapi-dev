package br.com.crux.cmd;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import br.com.crux.dao.repository.ContasBancariaRepository;
import br.com.crux.exception.ParametroNaoInformadoException;
import br.com.crux.exception.TabaleReferenciaEncontradaException;

@Component
public class ExcluirContasBancariaCmd {

	@Autowired private ContasBancariaRepository repository;

	public void excluir(Long id) {

		try {
			if (Objects.isNull(id)) {
				throw new ParametroNaoInformadoException("Erro ao excluir a entidade. Parâmetro 'id' ausente.");
			}
			repository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new TabaleReferenciaEncontradaException("Erro ao excluir, verifique se há outro cadastro com referência a esta entidade.");
		}

	}
}
