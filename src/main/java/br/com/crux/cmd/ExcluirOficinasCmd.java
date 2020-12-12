package br.com.crux.cmd;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.dao.repository.OficinasRepository;
import br.com.crux.exception.ParametroNaoInformadoException;

@Component
public class ExcluirOficinasCmd {

	@Autowired private OficinasRepository repository;

	public void excluir(Long idAtividade) {
		if (Objects.isNull(idAtividade)) {
			throw new ParametroNaoInformadoException("Erro ao excluir a Atividade. Parâmetro 'atividade' ausente.");
		}
	
		repository.deleteById(idAtividade);
	}
}
