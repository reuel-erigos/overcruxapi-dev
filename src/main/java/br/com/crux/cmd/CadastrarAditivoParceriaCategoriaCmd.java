package br.com.crux.cmd;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.AditivoParceriaCategoriaTOBuilder;
import br.com.crux.dao.repository.AditivoParceriaCategoriaRepository;
import br.com.crux.entity.AditivoParceriaCategoria;
import br.com.crux.entity.ParceriasCategorias;
import br.com.crux.to.AditivoParceriaCategoriaTO;

@Component
public class CadastrarAditivoParceriaCategoriaCmd {

	@Autowired
	AditivoParceriaCategoriaRepository repository;
	@Autowired
	AditivoParceriaCategoriaTOBuilder builder;

	public AditivoParceriaCategoria cadastrar(ParceriasCategorias p, AditivoParceriaCategoriaTO to) {
		AditivoParceriaCategoria entity = builder.build(p, to);
		return repository.save(entity);
	}

	public List<AditivoParceriaCategoria> cadastrarLista(ParceriasCategorias programa,
			List<AditivoParceriaCategoriaTO> ParceriasCategorias) {
		return ParceriasCategorias.stream().map(parceriaPrograma -> cadastrar(programa, parceriaPrograma))
				.collect(Collectors.toList());

	}

}
