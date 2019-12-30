package br.com.crux.cmd;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.ComposicaoRhProjetoTOBuilder;
import br.com.crux.dao.repository.ComposicaoRhProjetoRepository;
import br.com.crux.entity.ComposicaoRhProjeto;
import br.com.crux.entity.Projeto;
import br.com.crux.to.ComposicaoRhProjetoTO;

@Component
public class CadastrarComposicaoRhProjetoCmd {

	@Autowired
	ComposicaoRhProjetoRepository composicaoRhProjetoRepository;
	@Autowired
	ComposicaoRhProjetoTOBuilder composicaoRhProjetoTOBuilder;

	public ComposicaoRhProjeto cadastrar(Projeto projeto, ComposicaoRhProjetoTO composicaoRhProjetoTO) {
		ComposicaoRhProjeto composicaoRhProjeto = composicaoRhProjetoTOBuilder.build(projeto, composicaoRhProjetoTO);
		return composicaoRhProjetoRepository.save(composicaoRhProjeto);
	}

	public List<ComposicaoRhProjeto> cadastrarLista(Projeto projeto, List<ComposicaoRhProjetoTO> composicaoRhProjetoTO) {
		return composicaoRhProjetoTO.stream().map(composicaoRhProjeto -> cadastrar(projeto, composicaoRhProjeto)).collect(Collectors.toList());

	}

}
