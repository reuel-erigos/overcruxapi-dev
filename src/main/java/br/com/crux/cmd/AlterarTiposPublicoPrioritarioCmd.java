package br.com.crux.cmd;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.TiposPublicoPrioritarioTOBuilder;
import br.com.crux.dao.repository.TiposPublicoPrioritarioRepository;
import br.com.crux.entity.TiposPublicoPrioritario;
import br.com.crux.exception.NotFoundException;
import br.com.crux.rule.CamposObrigatoriosTiposPublicoPrioritarioRule;
import br.com.crux.to.TiposPublicoPrioritarioTO;

@Component
public class AlterarTiposPublicoPrioritarioCmd {
	
	@Autowired private TiposPublicoPrioritarioRepository repository;
	@Autowired private TiposPublicoPrioritarioTOBuilder toBuilder;
	@Autowired private CamposObrigatoriosTiposPublicoPrioritarioRule camposObrigatoriosRule;
	
	
	public void alterar(TiposPublicoPrioritarioTO to) {
		Optional<TiposPublicoPrioritario> entityOptional = repository.findById(to.getId());
		
		if(!entityOptional.isPresent()) {
			throw new NotFoundException("Motivo Desligamento informado não existe.");
		}
		
		camposObrigatoriosRule.verificar(to);
		
		TiposPublicoPrioritario entity = entityOptional.get();
		
		entity = toBuilder.build(to);
		
		repository.save(entity);
		
	}
}
