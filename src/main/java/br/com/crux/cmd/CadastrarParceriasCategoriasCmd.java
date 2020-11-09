package br.com.crux.cmd;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.ParceriasCategoriasTOBuilder;
import br.com.crux.dao.repository.ParceriasCategoriasRepository;
import br.com.crux.entity.ParceriasCategorias;
import br.com.crux.entity.ParceriasPrograma;
import br.com.crux.entity.ParceriasProjeto;
import br.com.crux.to.ParceriasCategoriasTO;

@Component
public class CadastrarParceriasCategoriasCmd {

	@Autowired ParceriasCategoriasRepository repository;
	@Autowired ParceriasCategoriasTOBuilder toBuilder;
	@Autowired AlterarAditivoParceriaCategoriaCmd alterarAditivoParceriaCategoriaCmd;
	

	public ParceriasCategorias cadastrar(ParceriasPrograma parceriasPrograma, ParceriasProjeto parceriasProjeto, ParceriasCategoriasTO to) {
		ParceriasCategorias entity = toBuilder.buildEntity(parceriasPrograma, null, to);
		
		alterarAditivoParceriaCategoriaCmd.alterarAll(to.getAditivosParceriasCategorias(), entity);
		
		ParceriasCategorias pc = repository.save(entity);
		
		return pc;
	}

	public List<ParceriasCategorias> cadastrarLista(ParceriasPrograma parceriasPrograma, ParceriasProjeto parceriasProjeto, List<ParceriasCategoriasTO> list) {
		return list.stream()
				.map(to -> cadastrar(parceriasPrograma, parceriasProjeto, to))
				.collect(Collectors.toList());

	}

}
