package br.com.crux.cmd;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.ParceriasProgramaTOBuilder;
import br.com.crux.dao.repository.ParceriasProgramaRepository;
import br.com.crux.entity.ParceriasPrograma;
import br.com.crux.entity.Programa;
import br.com.crux.to.ParceriasProgramaTO;

@Component
public class GetParceriasProgramaCmd {

	@Autowired private ParceriasProgramaRepository repository;
	@Autowired private ParceriasProgramaTOBuilder toBuilder;

	public List<ParceriasProgramaTO> getColaboradoresProgramaTOByPrograma(Programa programa) {
		Optional<List<ParceriasPrograma>> lista = repository.findByPrograma(programa);

		if (lista.isPresent()) {
			return toBuilder.buildAll(lista.get());
		}

		return Collections.emptyList();

	}

	public ParceriasPrograma get(Long id) {

		return repository.findById(id).orElse(null);
	}

}
