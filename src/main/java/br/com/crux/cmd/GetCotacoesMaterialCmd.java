package br.com.crux.cmd;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.CotacoesMaterialTOBuilder;
import br.com.crux.dao.repository.CotacoesMaterialRepository;
import br.com.crux.entity.CotacoesMaterial;
import br.com.crux.exception.NotFoundException;
import br.com.crux.to.CotacoesMaterialTO;

@Component
public class GetCotacoesMaterialCmd {

	@Autowired private CotacoesMaterialRepository repository;
	@Autowired private CotacoesMaterialTOBuilder toBuilder;

	public List<CotacoesMaterialTO> getAll() {

		List<CotacoesMaterial> entitys = repository.findAll();
		if (!entitys.isEmpty()) {
			return toBuilder.buildAll(entitys);
		}
		return new ArrayList<CotacoesMaterialTO>();

	}

	public CotacoesMaterialTO getTOById(Long id) {
		CotacoesMaterial cotacoesMaterial = repository.findById(id)
				.orElseThrow(() -> new NotFoundException("Entidade não encontrada"));
		return toBuilder.buildTO(cotacoesMaterial);
	}

	public CotacoesMaterial getById(Long id) {
		return repository.findById(id)
				.orElseGet(null);
	}

	public List<CotacoesMaterialTO> getPorMaterial(Long idMaterial) {
		List<CotacoesMaterial> entitys = repository.findByIdMaterial(idMaterial)
				.orElseThrow(() -> new NotFoundException("Nenhuma cotação para o material informado."));
		if (!entitys.isEmpty()) {
			return toBuilder.buildAll(entitys);
		}
		return new ArrayList<CotacoesMaterialTO>();

	}

}
