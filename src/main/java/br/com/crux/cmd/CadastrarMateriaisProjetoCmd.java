package br.com.crux.cmd;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.MateriaisProjetoTOBuilder;
import br.com.crux.dao.repository.MateriaisProjetoRepository;
import br.com.crux.entity.MateriaisProjeto;
import br.com.crux.entity.ParceriasProjeto;
import br.com.crux.entity.Projeto;
import br.com.crux.rule.CamposObrigatoriosMateriaisProjetoRule;
import br.com.crux.to.MateriaisProjetoTO;

@Component
public class CadastrarMateriaisProjetoCmd {

	@Autowired MateriaisProjetoRepository materiaisProjetoRepository;
	@Autowired MateriaisProjetoTOBuilder materiaisProjetoTOBuilder;
	@Autowired CamposObrigatoriosMateriaisProjetoRule camposObrigatoriosMateriaisProjetoRule;

	public MateriaisProjeto cadastrar(Projeto projeto, ParceriasProjeto parceriasProjeto, MateriaisProjetoTO materiaisProjetoTO) {
		camposObrigatoriosMateriaisProjetoRule.verificar(materiaisProjetoTO);
		MateriaisProjeto materiaisProjeto = materiaisProjetoTOBuilder.build(projeto, parceriasProjeto, materiaisProjetoTO);
		return materiaisProjetoRepository.save(materiaisProjeto);
	}

	public List<MateriaisProjeto> cadastrarLista(Projeto projeto, ParceriasProjeto parceriasProjeto, List<MateriaisProjetoTO> materiaisProjetoTO) {
		return materiaisProjetoTO.stream().map(composicaoRhProjeto -> cadastrar(projeto, parceriasProjeto, composicaoRhProjeto)).collect(Collectors.toList());

	}

}
