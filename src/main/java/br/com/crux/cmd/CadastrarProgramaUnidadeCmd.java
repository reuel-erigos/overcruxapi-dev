package br.com.crux.cmd;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.ProgramasUnidadeTOBuilder;
import br.com.crux.builder.UnidadeTOBuilder;
import br.com.crux.dao.repository.ProgramasUnidadeRepository;
import br.com.crux.entity.Programa;
import br.com.crux.entity.ProgramasUnidade;
import br.com.crux.entity.Unidade;
import br.com.crux.to.UnidadeTO;

@Component
public class CadastrarProgramaUnidadeCmd {

	@Autowired ProgramasUnidadeRepository programasUnidadeRepository;
	@Autowired UnidadeTOBuilder unidadeTOBuilder;
	@Autowired ProgramasUnidadeTOBuilder programasUnidadeTOBuilder;

	public ProgramasUnidade cadastrar(Programa programa, Unidade unidade) {
		ProgramasUnidade programaUnidade = programasUnidadeTOBuilder.build(programa, unidade);
		return programasUnidadeRepository.save(programaUnidade);
	}

	public List<ProgramasUnidade> cadastrarLista(Programa programa, List<UnidadeTO> unidadesTO) {
		return unidadesTO.stream().map(u -> cadastrar(programa, unidadeTOBuilder.build(u))).collect(Collectors.toList());

	}

}
