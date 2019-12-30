package br.com.crux.cmd;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.MaterialTOBuilder;
import br.com.crux.dao.repository.MaterialRepository;
import br.com.crux.entity.Material;
import br.com.crux.exception.NotFoundException;
import br.com.crux.to.MaterialTO;

@Component
public class GetMaterialCmd {

	@Autowired
	private MaterialRepository repository;
	@Autowired
	private MaterialTOBuilder toBuilder;

	public List<MaterialTO> getAll() {
		List<MaterialTO> entitys = toBuilder.buildAll(repository.findAll());
		if (entitys == null || entitys.isEmpty()) {
			return new ArrayList<MaterialTO>();
		}
		return entitys;
	}

	public MaterialTO getTOById(Long id) {
		Material entity = repository.findById(id).orElseThrow(() -> new NotFoundException("Material não encontrado."));
		return toBuilder.buildTO(entity);
	}

	public Material getById(Long id) {
		return repository.findById(id).orElseGet(null);
	}

}
