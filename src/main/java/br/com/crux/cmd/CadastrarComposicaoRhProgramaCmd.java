package br.com.crux.cmd;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.ComposicaoRhProgramaTOBuilder;
import br.com.crux.dao.repository.ComposicaoRhProgramaRepository;
import br.com.crux.entity.ComposicaoRhPrograma;
import br.com.crux.entity.Programa;
import br.com.crux.to.ComposicaoRhProgramaTO;

@Component
public class CadastrarComposicaoRhProgramaCmd {

	@Autowired ComposicaoRhProgramaRepository composicaoRhProgramaRepository;
	@Autowired ComposicaoRhProgramaTOBuilder composicaoRhProgramaTOBuilder;

	public ComposicaoRhPrograma cadastrar(Programa programa, ComposicaoRhProgramaTO composicaoRhProgramaTO) {
		ComposicaoRhPrograma composicaoRhPrograma = composicaoRhProgramaTOBuilder.build(programa, composicaoRhProgramaTO);
		return composicaoRhProgramaRepository.save(composicaoRhPrograma);
	}

	public List<ComposicaoRhPrograma> cadastrarLista(Programa programa, List<ComposicaoRhProgramaTO> list) {
		return list.stream().map(composicaoRhProjeto -> cadastrar(programa, composicaoRhProjeto)).collect(Collectors.toList());

	}

}
