package br.com.crux.cmd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.MaterialTOBuilder;
import br.com.crux.dao.repository.MaterialRepository;
import br.com.crux.entity.Material;
import br.com.crux.exception.NotFoundException;
import br.com.crux.to.MaterialTO;

@Component
public class AlterarMaterialCmd {

	@Autowired
	private MaterialRepository repository;
	@Autowired
	private MaterialTOBuilder materialTOBuilder;

	public void alterar(MaterialTO to) {
		Material material = repository.findById(to.getId()).orElseThrow(() -> new NotFoundException("Material informado não existe."));
		material = materialTOBuilder.build(to);
		repository.save(material);

	}
}
