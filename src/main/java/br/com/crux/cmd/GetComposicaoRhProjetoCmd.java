package br.com.crux.cmd;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.ComposicaoRhProjetoTOBuilder;
import br.com.crux.dao.repository.ComposicaoRhProjetoRepository;
import br.com.crux.entity.ComposicaoRhProjeto;
import br.com.crux.entity.Projeto;
import br.com.crux.to.ComposicaoRhProjetoTO;

@Component
public class GetComposicaoRhProjetoCmd {

	@Autowired
	private ComposicaoRhProjetoRepository repository;
	@Autowired
	private ComposicaoRhProjetoTOBuilder toBuilder;

	public List<ComposicaoRhProjetoTO> getComposicaoRhProjetoByProjeto(Projeto projeto) {
		Optional<List<ComposicaoRhProjeto>> lista = repository.findByProjeto(projeto);

		if (lista.isPresent()) {
			return toBuilder.buildAll(lista.get());
		}

		return Collections.emptyList();

	}

}
