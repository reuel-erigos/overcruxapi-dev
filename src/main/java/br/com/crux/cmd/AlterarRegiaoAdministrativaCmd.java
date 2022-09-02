package br.com.crux.cmd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.RegiaoAdministrativaTOBuilder;
import br.com.crux.dao.repository.RegiaoAdministrativaRepository;
import br.com.crux.entity.RegiaoAdministrativa;
import br.com.crux.exception.NotFoundException;
import br.com.crux.to.RegiaoAdministrativaTO;

@Component
public class AlterarRegiaoAdministrativaCmd {

	@Autowired
	private RegiaoAdministrativaRepository repository;
	@Autowired
	private RegiaoAdministrativaTOBuilder regiaoAdministrativaTOBuilder;

	public void alterar(RegiaoAdministrativaTO to) {
		RegiaoAdministrativa regiaoAdministrativa = repository.findById(to.getId()).orElseThrow(() -> new NotFoundException("Região Administrativa informada não existe."));
		regiaoAdministrativa = regiaoAdministrativaTOBuilder.build(to);
		repository.save(regiaoAdministrativa);

	}
}
