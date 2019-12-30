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

	@Autowired ParceriasProjetoRepository projetosUnidadeRepository;
	@Autowired ParceriasProjetoTOBuilder parceriasProjetoTOBuilder;
	@Autowired AlterarMateriaisParceriaProjetoCmd alterarMateriaisParceriaProjetoCmd;

	public ParceriasProjeto cadastrar(Projeto projeto, ParceriasProjetoTO parceriaProjeto) {
		ParceriasProjeto entity = parceriasProjetoTOBuilder.build(projeto, parceriaProjeto);
		ParceriasProjeto parceriasProjeto = projetosUnidadeRepository.save(entity);
		alterarMateriaisParceriaProjetoCmd.alterarAll(projeto, parceriasProjeto, parceriaProjeto.getMateriaisProjeto());
		return entity;
	}

	public List<ParceriasProjeto> cadastrarLista(Projeto projeto, List<ParceriasProjetoTO> parceriasProjeto) {
		return parceriasProjeto.stream().map(parceriaProjeto -> cadastrar(projeto, parceriaProjeto)).collect(Collectors.toList());

	}

}
