package br.com.crux.cmd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.RegiaoAdministrativaTOBuilder;
import br.com.crux.dao.repository.RegiaoAdministrativaRepository;
import br.com.crux.entity.RegiaoAdministrativa;
import br.com.crux.to.RegiaoAdministrativaTO;

@Component
public class CadastrarRegiaoAdministrativaCmd {

	@Autowired
	private RegiaoAdministrativaRepository repository;
	@Autowired
	private RegiaoAdministrativaTOBuilder materialTOBuilder;

	public void cadastrar(RegiaoAdministrativaTO param) {
		RegiaoAdministrativa entity = materialTOBuilder.build(param);
		repository.save(entity);
	}
}
