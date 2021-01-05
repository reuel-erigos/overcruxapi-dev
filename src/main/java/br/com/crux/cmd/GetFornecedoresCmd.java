				
package br.com.crux.cmd;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.FornecedoresTOBuilder;
import br.com.crux.dao.repository.FornecedoresRepository;
import br.com.crux.entity.Fornecedor;
import br.com.crux.to.FornecedorTO;

@Component
public class GetFornecedoresCmd {

	@Autowired private FornecedoresRepository repository;
	@Autowired private FornecedoresTOBuilder toBuilder;
	
	public List<FornecedorTO> getAllTO() {
		List<Fornecedor> lista = repository.findAll();
		if (!lista.isEmpty()) {
			return toBuilder.buildAll(lista);
		}
		return new ArrayList<FornecedorTO>();
	}

	public FornecedorTO getTOById(Long id) {
		Fornecedor entity = getById(id);
		if(Objects.nonNull(entity)) {
			return toBuilder.buildTO(entity);
		}
		return null;
	}
	
	public Fornecedor getById(Long id) {
		return repository.findById(id).orElseGet(null);
	}
	
}
