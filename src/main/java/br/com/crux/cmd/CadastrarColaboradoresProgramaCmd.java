package br.com.crux.cmd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.ColaboradoresProgramaTOBuilder;
import br.com.crux.dao.repository.ColaboradoresProgramaRepository;
import br.com.crux.entity.ColaboradoresPrograma;
import br.com.crux.entity.Programa;
import br.com.crux.rule.CamposObrigatoriosColaboradoresProgramaRule;
import br.com.crux.to.ColaboradoresProgramaTO;

@Component
public class CadastrarColaboradoresProgramaCmd {

	@Autowired private ColaboradoresProgramaRepository colaboradoresProgramaRepository;
	@Autowired private CamposObrigatoriosColaboradoresProgramaRule camposObrigatoriosColaboradoresProgramaRule;
	@Autowired private ColaboradoresProgramaTOBuilder colaboradoresProgramaTOBuilder;

	public void cadastrar(ColaboradoresProgramaTO colaborador, Programa programa) {
		camposObrigatoriosColaboradoresProgramaRule.verificar(colaborador);
		ColaboradoresPrograma entity = colaboradoresProgramaTOBuilder.build(programa, colaborador);
		colaboradoresProgramaRepository.save(entity);
	}

}
