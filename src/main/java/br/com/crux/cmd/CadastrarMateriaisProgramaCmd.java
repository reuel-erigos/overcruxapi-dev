package br.com.crux.cmd;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.MateriaisProgramaTOBuilder;
import br.com.crux.dao.repository.MateriaisProgramaRepository;
import br.com.crux.entity.MateriaisPrograma;
import br.com.crux.entity.ParceriasPrograma;
import br.com.crux.entity.Programa;
import br.com.crux.rule.CamposObrigatoriosMateriaisProgramaRule;
import br.com.crux.to.MateriaisProgramaTO;

@Component
public class CadastrarMateriaisProgramaCmd {

	@Autowired MateriaisProgramaRepository materiaisProgramaRepository;
	@Autowired MateriaisProgramaTOBuilder materiaisProgramaTOBuilder;
	@Autowired CamposObrigatoriosMateriaisProgramaRule camposObrigatoriosMateriaisProgramaRule;

	public MateriaisPrograma cadastrar(Programa programa, ParceriasPrograma parceriasPrograma, MateriaisProgramaTO materiaisProgramaTO) {
		camposObrigatoriosMateriaisProgramaRule.verificar(materiaisProgramaTO);
		MateriaisPrograma materiaisPrograma = materiaisProgramaTOBuilder.build(programa, parceriasPrograma, materiaisProgramaTO);
		return materiaisProgramaRepository.save(materiaisPrograma);
	}

	public List<MateriaisPrograma> cadastrarLista(Programa programa, ParceriasPrograma parceriasPrograma, List<MateriaisProgramaTO> materiaisProgramaTO) {
		return materiaisProgramaTO.stream().map(composicaoRhPrograma -> cadastrar(programa, parceriasPrograma, composicaoRhPrograma)).collect(Collectors.toList());

	}

}
