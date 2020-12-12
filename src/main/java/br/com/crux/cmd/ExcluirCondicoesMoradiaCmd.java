package br.com.crux.cmd;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.dao.repository.CondicoesMoradiaRepository;
import br.com.crux.exception.ParametroNaoInformadoException;

@Component
public class ExcluirCondicoesMoradiaCmd {

	@Autowired
	private CondicoesMoradiaRepository repository;
	
	
	public void excluir(Long id) {
		if(Objects.isNull(id)) {
			throw new ParametroNaoInformadoException("Erro ao excluir. Parâmetro ausente.");
		}
		repository.deleteById(id);
	}
}
