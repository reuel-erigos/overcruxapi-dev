package br.com.crux.cmd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.MaterialTOBuilder;
import br.com.crux.dao.repository.MaterialRepository;
import br.com.crux.entity.Material;
import br.com.crux.to.MaterialTO;

@Component
public class CadastrarMaterialCmd {

	@Autowired
	private MaterialRepository repository;
	@Autowired
	private MaterialTOBuilder materialTOBuilder;

	public void cadastrar(MaterialTO param) {
		Material entity = materialTOBuilder.build(param);
		repository.save(entity);
	}
}
