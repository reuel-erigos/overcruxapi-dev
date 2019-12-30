package br.com.crux.cmd;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.ColaboradoresProgramaTOBuilder;
import br.com.crux.builder.ProjetosUnidadeTOBuilder;
import br.com.crux.builder.UnidadeTOBuilder;
import br.com.crux.dao.repository.ColaboradoresProgramaRepository;
import br.com.crux.entity.ColaboradoresPrograma;
import br.com.crux.entity.Programa;
import br.com.crux.to.ColaboradoresProgramaTO;

@Component
public class CadastrarListaColaboradoresProgramaCmd {

	@Autowired ColaboradoresProgramaRepository colaboradoresProgramaRepository;
	@Autowired UnidadeTOBuilder unidadeTOBuilder;
	@Autowired ColaboradoresProgramaTOBuilder colaboradoresProgramaTOBuilder;
	@Autowired ProjetosUnidadeTOBuilder projetosUnidadeTOBuilder;

	public ColaboradoresPrograma cadastrar(ColaboradoresPrograma colaboradoresPrograma) {
		return colaboradoresProgramaRepository.save(colaboradoresPrograma);
	}

	public List<ColaboradoresPrograma> cadastrarLista(Programa programa, List<ColaboradoresProgramaTO> list) {
		return list.stream().map(cd -> colaboradoresProgramaTOBuilder.build(programa, cd)).map(this::cadastrar).collect(Collectors.toList());

	}

}
