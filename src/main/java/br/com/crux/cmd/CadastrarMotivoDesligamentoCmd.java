package br.com.crux.cmd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.MotivoDesligamentoTOBuilder;
import br.com.crux.dao.repository.MotivoDesligamentoRepository;
import br.com.crux.entity.MotivoDesligamento;
import br.com.crux.rule.CamposObrigatoriosMotivoDesligamentoRule;
import br.com.crux.to.MotivoDesligamentoTO;

@Component
public class CadastrarMotivoDesligamentoCmd {
	
	@Autowired private MotivoDesligamentoRepository repository;
	@Autowired private MotivoDesligamentoTOBuilder toBuilder;
	@Autowired private CamposObrigatoriosMotivoDesligamentoRule camposObrigatoriosRule;
	
	
	public void cadastrar(MotivoDesligamentoTO to) {
		
		camposObrigatoriosRule.verificar(to);
		
		MotivoDesligamento entity = toBuilder.build(to);
		
		repository.save(entity);
		
	}
}
