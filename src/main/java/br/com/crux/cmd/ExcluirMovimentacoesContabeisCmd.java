package br.com.crux.cmd;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.dao.repository.MovimentacoesContabeisRepository;
import br.com.crux.exception.ParametroNaoInformadoException;

@Component
public class ExcluirMovimentacoesContabeisCmd {

	@Autowired private MovimentacoesContabeisRepository repository;

	public void excluir(Long id) {
		if (Objects.isNull(id)) {
			throw new ParametroNaoInformadoException("Erro ao excluir a entidade. Parâmetro 'id' ausente.");
		}
		repository.deleteById(id);
	}
}
