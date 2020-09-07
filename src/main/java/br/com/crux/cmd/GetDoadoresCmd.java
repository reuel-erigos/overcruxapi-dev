				
package br.com.crux.cmd;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.DoadoresTOBuilder;
import br.com.crux.dao.repository.DoadoresRepository;
import br.com.crux.entity.Doadores;
import br.com.crux.to.DoadoresTO;

@Component
public class GetDoadoresCmd {

	@Autowired private DoadoresRepository repository;
	@Autowired private DoadoresTOBuilder toBuilder;
	@Autowired private GetUnidadeLogadaCmd getUnidadeLogadaCmd;
	
	
	public List<DoadoresTO> getAllTO() {
		Long idInstituicao = getUnidadeLogadaCmd.getUnidadeTO().getInstituicao().getId();
		Optional<List<Doadores>> lista = repository.findByIdInstituicao(idInstituicao);
		if (lista.isPresent()) {
			return toBuilder.buildAll(lista.get());
		}
		return new ArrayList<DoadoresTO>();
	}

	public DoadoresTO getTOById(Long id) {
		Doadores entity = getById(id);
		if(Objects.nonNull(entity)) {
			return toBuilder.buildTO(entity);
		}
		return null;
	}
	
	public Doadores getById(Long id) {
		return repository.findById(id).orElseGet(null);
	}
}
