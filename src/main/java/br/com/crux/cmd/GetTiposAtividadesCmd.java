				
package br.com.crux.cmd;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.TiposAtividadesTOBuilder;
import br.com.crux.dao.repository.TiposAtividadesRepository;
import br.com.crux.entity.TiposAtividades;
import br.com.crux.exception.NotFoundException;
import br.com.crux.to.TiposAtividadesTO;

@Component
public class GetTiposAtividadesCmd {

	@Autowired private TiposAtividadesRepository repository;
	@Autowired private TiposAtividadesTOBuilder toBuilder;

	public List<TiposAtividadesTO> getAll() {
		List<TiposAtividades> lista = repository.findAll();
		if(lista.isEmpty()) {
			return Collections.emptyList();
		}
			return toBuilder.buildAll(lista);
	}

	public TiposAtividades getById(Long id) {
		return repository.findById(id).orElse(null);
	}

	public TiposAtividadesTO getTOById(Long id) {
		TiposAtividades entity = repository.findById(id).orElseThrow(() -> new NotFoundException("Tipo Atividade não encontrado."));
		return toBuilder.buildTO(entity);
	}
}
