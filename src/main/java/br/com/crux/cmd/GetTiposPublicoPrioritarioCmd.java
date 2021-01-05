				
package br.com.crux.cmd;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.TiposPublicoPrioritarioTOBuilder;
import br.com.crux.dao.repository.TiposPublicoPrioritarioRepository;
import br.com.crux.entity.TiposPublicoPrioritario;
import br.com.crux.exception.NotFoundException;
import br.com.crux.to.TiposPublicoPrioritarioTO;

@Component
public class GetTiposPublicoPrioritarioCmd {

	@Autowired private TiposPublicoPrioritarioRepository repository;
	@Autowired private TiposPublicoPrioritarioTOBuilder toBuilder;
	@Autowired private GetUnidadeLogadaCmd getUnidadeLogadaCmd;

	public List<TiposPublicoPrioritarioTO> getAll() {
		Long idInstituicao = getUnidadeLogadaCmd.getUnidadeTO().getInstituicao().getId();
		List<TiposPublicoPrioritario> lista = repository.findByInstituicao(idInstituicao);
		if(lista.isEmpty()) {
			return Collections.emptyList();
		}
			return toBuilder.buildTO(lista);
	}

	public TiposPublicoPrioritario getById(Long id) {
		return repository.findById(id).orElse(null);
	}

	public TiposPublicoPrioritarioTO getTOById(Long id) {
		TiposPublicoPrioritario entity = repository.findById(id).orElseThrow(() -> new NotFoundException("Situação do ex aluno não encontrada."));
		return toBuilder.buildTO(entity);
	}
}
