package br.com.crux.cmd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.TiposDoadoresTOBuilder;
import br.com.crux.dao.repository.TiposDoadoresRepository;
import br.com.crux.entity.TiposDoadores;
import br.com.crux.rule.CamposObrigatoriosTiposDoadoresRule;
import br.com.crux.to.TiposDoadoresTO;

@Component
public class CadastrarTiposDoadoresCmd {
	
	@Autowired private TiposDoadoresRepository repository;
	@Autowired private TiposDoadoresTOBuilder toBuilder;
	@Autowired private CamposObrigatoriosTiposDoadoresRule camposObrigatoriosRule;
	
	
	public void cadastrar(TiposDoadoresTO to) {
		camposObrigatoriosRule.verificar(to);
		
		TiposDoadores entity = toBuilder.build(to);
		
		repository.save(entity);
		
	}
}
