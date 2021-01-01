package br.com.crux.cmd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.FornecedoresTOBuilder;
import br.com.crux.dao.repository.FornecedoresRepository;
import br.com.crux.entity.Fornecedor;
import br.com.crux.to.FornecedorTO;

@Component
public class CadastrarFornecedoresCmd {

	@Autowired private FornecedoresRepository repository;
	@Autowired private FornecedoresTOBuilder toBuilder;

	
	public FornecedorTO cadastrar(FornecedorTO to) {

		Fornecedor Fornecedores = toBuilder.build(to);

		Fornecedor entitySalva = repository.save(Fornecedores);		
		
		return toBuilder.buildTO(entitySalva);
	}
}
