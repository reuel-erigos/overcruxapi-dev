package br.com.crux.cmd;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.MateriaisProgramaTOBuilder;
import br.com.crux.dao.repository.MateriaisProgramaRepository;
import br.com.crux.entity.MateriaisPrograma;
import br.com.crux.entity.ParceriasPrograma;
import br.com.crux.to.MateriaisProgramaTO;

@Component
public class GetMateriaisParceirosProgramaCmd {

	@Autowired private MateriaisProgramaRepository repository;
	@Autowired private MateriaisProgramaTOBuilder toBuilder;

	public List<MateriaisProgramaTO> getMateriaisProgramaTOByParceriasPrograma(ParceriasPrograma parceriasPrograma) {
		Optional<List<MateriaisPrograma>> lista = repository.findByParceriasPrograma(parceriasPrograma);

		if (lista.isPresent()) {
			return toBuilder.buildAll(lista.get());
		}

		return Collections.emptyList();

	}

	public List<MateriaisPrograma> getMateriaisProgramaByParceriasPrograma(ParceriasPrograma parceriasPrograma) {
		return repository.findByParceriasPrograma(parceriasPrograma).orElse(new ArrayList<MateriaisPrograma>());

	}

}
