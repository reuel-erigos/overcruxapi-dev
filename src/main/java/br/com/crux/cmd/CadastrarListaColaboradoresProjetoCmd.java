package br.com.crux.cmd;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.ColaboradoresProjetoTOBuilder;
import br.com.crux.builder.ProjetosUnidadeTOBuilder;
import br.com.crux.builder.UnidadeTOBuilder;
import br.com.crux.dao.repository.ColaboradoresProjetoRepository;
import br.com.crux.entity.ColaboradoresProjeto;
import br.com.crux.entity.Projeto;
import br.com.crux.to.ColaboradoresProjetoTO;

@Component
public class CadastrarListaColaboradoresProjetoCmd {

	@Autowired ColaboradoresProjetoRepository colaboradoresProjetoRepository;
	@Autowired UnidadeTOBuilder unidadeTOBuilder;
	@Autowired ColaboradoresProjetoTOBuilder colaboradoresProjetoTOBuilder;
	@Autowired ProjetosUnidadeTOBuilder projetosUnidadeTOBuilder;

	public ColaboradoresProjeto cadastrar(ColaboradoresProjeto colaboradoresProjeto) {
		return colaboradoresProjetoRepository.save(colaboradoresProjeto);
	}

	public List<ColaboradoresProjeto> cadastrarLista(Projeto projeto, List<ColaboradoresProjetoTO> colaboradoresProjetoTO) {
		return colaboradoresProjetoTO.stream()
									 .map(cd -> colaboradoresProjetoTOBuilder.build(projeto,cd))
									 .map(this::cadastrar)
									 .collect(Collectors.toList());

	}

}
