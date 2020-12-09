
package br.com.crux.cmd;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.EstruturaUnidadeTOBuilder;
import br.com.crux.dao.repository.EstruturaUnidadeRepository;
import br.com.crux.entity.EstruturaUnidade;
import br.com.crux.entity.Unidade;
import br.com.crux.exception.NotFoundException;
import br.com.crux.to.EstruturaUnidadeTO;

@Component
public class GetEstruturaUnidadeCmd {

	@Autowired
	private EstruturaUnidadeRepository repository;
	@Autowired
	private EstruturaUnidadeTOBuilder toBuilder;

	public List<EstruturaUnidadeTO> getAll() {
		List<EstruturaUnidade> lista = repository.findAll();
		if (lista.isEmpty()) {
			return Collections.emptyList();
		}
		return toBuilder.buildAll(lista);
	}

	public EstruturaUnidade getById(Long id) {
		return repository.findById(id).orElse(null);
	}

	public List<EstruturaUnidade> getByUnidade(Unidade unidade) {
		return repository.findByUnidade(unidade).orElse(new ArrayList<EstruturaUnidade>());
	}

	public EstruturaUnidadeTO getTOById(Long id) {
		EstruturaUnidade entity = repository.findById(id).orElseThrow(
				() -> new NotFoundException("Estrutura Unidade não encontrada."));
		return toBuilder.buildTO(entity);
	}
	
	
}
