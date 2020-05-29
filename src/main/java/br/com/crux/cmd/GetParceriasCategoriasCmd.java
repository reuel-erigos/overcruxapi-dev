package br.com.crux.cmd;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.ParceriasCategoriasTOBuilder;
import br.com.crux.dao.repository.ParceriasCategoriasRepository;
import br.com.crux.entity.ParceriasCategorias;
import br.com.crux.entity.ParceriasPrograma;
import br.com.crux.entity.ParceriasProjeto;
import br.com.crux.to.ParceriasCategoriasTO;

@Component
public class GetParceriasCategoriasCmd {

	@Autowired private ParceriasCategoriasRepository repository;
	@Autowired private ParceriasCategoriasTOBuilder toBuilder;

	public ParceriasCategorias get(Long id) {

		return repository.findById(id)
				.orElse(null);
	}

	public List<ParceriasCategoriasTO> getParceriasCategoriasTOByParceriasPrograma(ParceriasPrograma pp) {

		List<ParceriasCategorias> lista = repository.findByParceriasPrograma(pp)
				.orElse(Collections.emptyList());

		return toBuilder.buildAllTO(lista);

	}

	public List<ParceriasCategorias> getParceriasCategoriasByParceriasPrograma(ParceriasPrograma pp) {

		return repository.findByParceriasPrograma(pp)
				.orElse(Collections.emptyList());


	}

	
	public List<ParceriasCategoriasTO> getParceriasCategoriasTOByParceriasProjeto(ParceriasProjeto parceriaProjeto) {
		List<ParceriasCategorias> lista = repository.findByParceriasProjeto(parceriaProjeto)
				.orElse(Collections.emptyList());

		return toBuilder.buildAllTO(lista);

	}

	public List<ParceriasCategorias> getParceriasCategoriasByParceriasProjeto(ParceriasProjeto parceriaProjeto) {
		return repository.findByParceriasProjeto(parceriaProjeto)
				.orElse(Collections.emptyList());
		
	}

}
