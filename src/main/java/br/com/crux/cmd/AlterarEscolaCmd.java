package br.com.crux.cmd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.EscolaTOBuilder;
import br.com.crux.dao.repository.EscolaRepository;
import br.com.crux.entity.Escola;
import br.com.crux.exception.NotFoundException;
import br.com.crux.to.EscolaTO;

@Component
public class AlterarEscolaCmd {

	@Autowired
	private EscolaRepository repository;
	@Autowired
	private EscolaTOBuilder regiaoAdministrativaTOBuilder;

	public void alterar(EscolaTO to) {
		Escola entity = repository.findById(to.getId()).orElseThrow(() -> new NotFoundException("Escola informada não existe."));
		entity = regiaoAdministrativaTOBuilder.build(to);
		repository.save(entity);

	}
}
