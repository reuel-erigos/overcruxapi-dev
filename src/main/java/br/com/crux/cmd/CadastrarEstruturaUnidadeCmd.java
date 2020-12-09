package br.com.crux.cmd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.EstruturaUnidadeTOBuilder;
import br.com.crux.dao.repository.EstruturaUnidadeRepository;
import br.com.crux.entity.EstruturaUnidade;
import br.com.crux.entity.Unidade;
import br.com.crux.rule.CamposObrigatoriosEstruturaUnidadeRule;
import br.com.crux.to.EstruturaUnidadeTO;

@Component
public class CadastrarEstruturaUnidadeCmd {
	
	@Autowired private EstruturaUnidadeRepository repository;
	@Autowired private EstruturaUnidadeTOBuilder toBuilder;
	@Autowired private CamposObrigatoriosEstruturaUnidadeRule camposObrigatoriosRule;
	
	
	public void cadastrar(Unidade unidade, EstruturaUnidadeTO to) {
		
		camposObrigatoriosRule.verificar(to,unidade);
		
		EstruturaUnidade entity = toBuilder.build(to,unidade);
		
		repository.save(entity);
		
	}
}
