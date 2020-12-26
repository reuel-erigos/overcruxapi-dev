package br.com.crux.cmd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.TiposPublicoPrioritarioTOBuilder;
import br.com.crux.dao.repository.TiposPublicoPrioritarioRepository;
import br.com.crux.entity.TiposPublicoPrioritario;
import br.com.crux.rule.CamposObrigatoriosTiposPublicoPrioritarioRule;
import br.com.crux.to.TiposPublicoPrioritarioTO;

@Component
public class CadastrarTiposPublicoPrioritarioCmd {
	
	@Autowired private TiposPublicoPrioritarioRepository repository;
	@Autowired private TiposPublicoPrioritarioTOBuilder toBuilder;
	@Autowired private CamposObrigatoriosTiposPublicoPrioritarioRule camposObrigatoriosRule;
	
	public void cadastrar(TiposPublicoPrioritarioTO to) {
		
		camposObrigatoriosRule.verificar(to);
		
		TiposPublicoPrioritario entity = toBuilder.build(to);
		
		repository.save(entity);
		
	}
}
