package br.com.crux.cmd;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.DoadoresTOBuilder;
import br.com.crux.dao.repository.DoadoresRepository;
import br.com.crux.entity.Doadores;
import br.com.crux.to.DoadoresTO;

@Component
public class CadastrarDoadoresCmd {

	@Autowired private DoadoresRepository repository;
	@Autowired private DoadoresTOBuilder toBuilder;

	
	public DoadoresTO cadastrar(DoadoresTO to) {
		to.setDataInicioVinculo(LocalDateTime.now());
		Doadores doadores = toBuilder.build(to);

		Doadores entitySalva = repository.save(doadores);		
		return toBuilder.buildTO(entitySalva);
	}
}
