package br.com.crux.cmd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.DoadoresTOBuilder;
import br.com.crux.dao.repository.DoadoresRepository;
import br.com.crux.entity.Doadores;
import br.com.crux.exception.NotFoundException;
import br.com.crux.to.DoadoresTO;

@Component
public class AlterarDoadoresCmd {

	@Autowired private DoadoresRepository repository;
	@Autowired private DoadoresTOBuilder toBuilder;

	
	public DoadoresTO alterar(DoadoresTO to) {
		Doadores doadores = repository.findById(to.getId()).orElseThrow(() -> new NotFoundException("Doador informado não existe."));

		doadores = toBuilder.build(to);
		Doadores entitySalva = repository.save(doadores);
		return toBuilder.buildTO(entitySalva);
	}

}
