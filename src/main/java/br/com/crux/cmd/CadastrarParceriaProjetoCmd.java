package br.com.crux.cmd;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.ParceriasProjetoTOBuilder;
import br.com.crux.dao.repository.ParceriasProjetoRepository;
import br.com.crux.entity.ParceriasProjeto;
import br.com.crux.entity.Projeto;
import br.com.crux.to.ParceriasProjetoTO;

@Component
public class CadastrarParceriaProjetoCmd {

	@Autowired ParceriasProjetoRepository parceriasProjetoRepository;
	@Autowired ParceriasProjetoTOBuilder parceriasProjetoTOBuilder;
	@Autowired AlterarMateriaisParceriaProjetoCmd alterarMateriaisParceriaProjetoCmd;
	@Autowired AlterarParceriasCategoriasProjetoCmd alterarParceriasCategoriasProjetoCmd;

	public ParceriasProjeto cadastrar(Projeto projeto, ParceriasProjetoTO to) {
		ParceriasProjeto entity = parceriasProjetoTOBuilder.buildEntity(projeto, to);
		ParceriasProjeto parceriasProjeto = parceriasProjetoRepository.save(entity);
		alterarMateriaisParceriaProjetoCmd.alterarAll(projeto, parceriasProjeto, to.getMateriaisProjeto());
		alterarParceriasCategoriasProjetoCmd.alterarAll(projeto, parceriasProjeto, to.getParceriasCategorias() );
		return entity;
	}

	public List<ParceriasProjeto> cadastrarLista(Projeto projeto, List<ParceriasProjetoTO> parceriasProjeto) {
		return parceriasProjeto.stream().map(parceriaProjeto -> cadastrar(projeto, parceriaProjeto)).collect(Collectors.toList());

	}

}
