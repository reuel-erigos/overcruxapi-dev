package br.com.crux.cmd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.TiposAtividadesTOBuilder;
import br.com.crux.dao.repository.TiposAtividadesRepository;
import br.com.crux.entity.TiposAtividades;
import br.com.crux.rule.CamposObrigatoriosTiposAtividadesRule;
import br.com.crux.to.TiposAtividadesTO;

@Component
public class CadastrarTiposAtividadesCmd {
	
	@Autowired private TiposAtividadesRepository repository;
	@Autowired private TiposAtividadesTOBuilder toBuilder;
	@Autowired private CamposObrigatoriosTiposAtividadesRule camposObrigatoriosRule;
	
	
	public void cadastrar(TiposAtividadesTO to) {
		
		camposObrigatoriosRule.verificar(to.getDescricao());
		
		TiposAtividades entity = toBuilder.build(to);
		
		repository.save(entity);
		
	}
}
