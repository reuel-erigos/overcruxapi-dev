package br.com.crux.cmd;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.TiposDoadoresTOBuilder;
import br.com.crux.dao.repository.TiposDoadoresRepository;
import br.com.crux.entity.TiposDoadores;
import br.com.crux.exception.NotFoundException;
import br.com.crux.rule.CamposObrigatoriosTiposDoadoresRule;
import br.com.crux.to.TiposDoadoresTO;

@Component
public class AlterarTiposDoadoresCmd {
	
	@Autowired private TiposDoadoresRepository repository;
	@Autowired private TiposDoadoresTOBuilder toBuilder;
	@Autowired private CamposObrigatoriosTiposDoadoresRule camposObrigatoriosRule;
	
	
	public void alterar(TiposDoadoresTO to) {
		Optional<TiposDoadores> entityOptional = repository.findById(to.getId());
		
		if(!entityOptional.isPresent()) {
			throw new NotFoundException("Situação ex aluno informada não existe.");
		}
		
		camposObrigatoriosRule.verificar(to);
		
		TiposDoadores entity = entityOptional.get();
		
		entity = toBuilder.build(to);
		
		repository.save(entity);
		
	}
}
