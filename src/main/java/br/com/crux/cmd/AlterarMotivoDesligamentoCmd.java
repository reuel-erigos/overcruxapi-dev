package br.com.crux.cmd;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.MotivoDesligamentoTOBuilder;
import br.com.crux.dao.repository.MotivoDesligamentoRepository;
import br.com.crux.entity.MotivoDesligamento;
import br.com.crux.exception.NotFoundException;
import br.com.crux.rule.CamposObrigatoriosMotivoDesligamentoRule;
import br.com.crux.to.MotivoDesligamentoTO;

@Component
public class AlterarMotivoDesligamentoCmd {
	
	@Autowired private MotivoDesligamentoRepository repository;
	@Autowired private MotivoDesligamentoTOBuilder toBuilder;
	@Autowired private CamposObrigatoriosMotivoDesligamentoRule camposObrigatoriosRule;
	
	
	public void alterar(MotivoDesligamentoTO to) {
		Optional<MotivoDesligamento> entityOptional = repository.findById(to.getId());
		
		if(!entityOptional.isPresent()) {
			throw new NotFoundException("Motivo Desligamento informado não existe.");
		}
		
		camposObrigatoriosRule.verificar(to);
		
		MotivoDesligamento entity = entityOptional.get();
		
		entity = toBuilder.build(to);
		
		repository.save(entity);
		
	}
}
