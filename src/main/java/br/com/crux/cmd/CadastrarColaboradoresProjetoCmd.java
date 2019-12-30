package br.com.crux.cmd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.ColaboradoresProjetoTOBuilder;
import br.com.crux.dao.repository.ColaboradoresProjetoRepository;
import br.com.crux.entity.ColaboradoresProjeto;
import br.com.crux.entity.Projeto;
import br.com.crux.rule.CamposObrigatoriosColaboradoresProjetoRule;
import br.com.crux.to.ColaboradoresProjetoTO;

@Component
public class CadastrarColaboradoresProjetoCmd {

	@Autowired
	private ColaboradoresProjetoRepository colaboradoresProjetoRepository;
	@Autowired
	private CamposObrigatoriosColaboradoresProjetoRule camposObrigatoriosColaboradoresProjetoRule;
	@Autowired
	private ColaboradoresProjetoTOBuilder colaboradoresProjetoTOBuilder;

	public void cadastrar(ColaboradoresProjetoTO colaborador, Projeto projeto) {
		camposObrigatoriosColaboradoresProjetoRule.verificar(colaborador);
		ColaboradoresProjeto entity = colaboradoresProjetoTOBuilder.build(projeto, colaborador);
		colaboradoresProjetoRepository.save(entity);
	}
}
