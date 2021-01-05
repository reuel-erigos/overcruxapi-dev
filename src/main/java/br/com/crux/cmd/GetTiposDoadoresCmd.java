				
package br.com.crux.cmd;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.TiposDoadoresTOBuilder;
import br.com.crux.dao.repository.TiposDoadoresRepository;
import br.com.crux.entity.TiposDoadores;
import br.com.crux.exception.NotFoundException;
import br.com.crux.to.TiposDoadoresTO;

@Component
public class GetTiposDoadoresCmd {

	@Autowired private TiposDoadoresRepository repository;
	@Autowired private GetUnidadeLogadaCmd getUnidadeLogadaCmd ;
	@Autowired private TiposDoadoresTOBuilder toBuilder;

	public List<TiposDoadoresTO> getAllTO() {
		Long idInstituicao = getUnidadeLogadaCmd.getUnidadeTO().getInstituicao().getId();
		
		Optional<List<TiposDoadores>> lista = repository.findByIdInstituicao(idInstituicao);
		if (lista.isPresent()) {
			return toBuilder.buildAll(lista.get());
		}
		return new ArrayList<TiposDoadoresTO>();
	}

	public TiposDoadores getById(Long id) {
		return repository.findById(id).orElse(null);
	}
	
	public TiposDoadoresTO getTOById(Long id) {
		TiposDoadores entity = repository.findById(id).orElseThrow(() -> new NotFoundException("Situação do ex aluno não encontrada."));
		return toBuilder.buildTO(entity);
	}
}
