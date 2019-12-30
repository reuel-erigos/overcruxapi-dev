package br.com.crux.cmd;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.ProjetosUnidadeTOBuilder;
import br.com.crux.builder.UnidadeTOBuilder;
import br.com.crux.dao.repository.ProjetosUnidadeRepository;
import br.com.crux.entity.Projeto;
import br.com.crux.entity.ProjetosUnidade;
import br.com.crux.entity.Unidade;
import br.com.crux.to.UnidadeTO;

@Component
public class CadastrarProjetosUnidadeCmd {

	@Autowired ProjetosUnidadeRepository projetosUnidadeRepository;
	@Autowired UnidadeTOBuilder unidadeTOBuilder;
	@Autowired ProjetosUnidadeTOBuilder projetosUnidadeTOBuilder;

	public ProjetosUnidade cadastrar(Projeto projeto, Unidade unidade) {
		ProjetosUnidade projetosUnidade = projetosUnidadeTOBuilder.build(projeto, unidade);
		return projetosUnidadeRepository.save(projetosUnidade);
	}

	public List<ProjetosUnidade> cadastrarLista(Projeto projeto, List<UnidadeTO> unidadesTO) {
		return unidadesTO.stream().map(u -> cadastrar(projeto, unidadeTOBuilder.build(u))).collect(Collectors.toList());

	}

}
