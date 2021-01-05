package br.com.crux.cmd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.FornecedoresTOBuilder;
import br.com.crux.dao.repository.FornecedoresRepository;
import br.com.crux.entity.Fornecedor;
import br.com.crux.exception.NotFoundException;
import br.com.crux.to.FornecedorTO;

@Component
public class AlterarFornecedoresCmd {

	@Autowired private FornecedoresRepository repository;
	@Autowired private FornecedoresTOBuilder toBuilder;

	
	public FornecedorTO alterar(FornecedorTO to) {
		Fornecedor Fornecedores = repository.findById(to.getId()).orElseThrow(() -> new NotFoundException("Doador informado não existe."));

		Fornecedores = toBuilder.build(to);
		Fornecedor entitySalva = repository.save(Fornecedores);
		return toBuilder.buildTO(entitySalva);
	}

}
