package br.com.crux.cmd;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.TiposAtividadesTOBuilder;
import br.com.crux.dao.repository.TiposAtividadesRepository;
import br.com.crux.entity.TiposAtividades;
import br.com.crux.exception.NotFoundException;
import br.com.crux.rule.CamposObrigatoriosTiposAtividadesRule;
import br.com.crux.to.TiposAtividadesTO;

@Component
public class AlterarTiposAtividadesCmd {
	
	@Autowired private TiposAtividadesRepository repository;
	@Autowired private TiposAtividadesTOBuilder toBuilder;
	@Autowired private CamposObrigatoriosTiposAtividadesRule camposObrigatoriosRule;
	
	
	
	public void alterar(TiposAtividadesTO to) {
		Optional<TiposAtividades> entityOptional = repository.findById(to.getId());
		
		if(!entityOptional.isPresent()) {
			throw new NotFoundException("Tipo atividade informada não existe.");
		}
		
		camposObrigatoriosRule.verificar(to.getDescricao());
		
		TiposAtividades entity = entityOptional.get();
		
		entity = toBuilder.build(to);
		
		repository.save(entity);
		
	}
}
