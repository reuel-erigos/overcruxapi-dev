package br.com.crux.cmd;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.TributosTOBuilder;
import br.com.crux.dao.repository.TributosRepository;
import br.com.crux.entity.Tributos;
import br.com.crux.exception.NotFoundException;
import br.com.crux.to.TributosTO;

@Component
public class GetTributoCmd {

	@Autowired private TributosRepository repository;
	@Autowired private TributosTOBuilder toBuilder;
	@Autowired private GetUnidadeLogadaCmd getUnidadeLogadaCmd;

	public List<TributosTO> getAllIntituicaoLogada() {
		Long idIntituicao = getUnidadeLogadaCmd.getUnidadeTO().getInstituicao().getId();

		Optional<List<Tributos>> entitys = repository.findByIdInstituicao(idIntituicao);
		if (entitys.isPresent()) {
			return toBuilder.buildAll(entitys.get());
		}
		return new ArrayList<TributosTO>();
	}

	public TributosTO getTOById(Long id) {
		Tributos entity = repository.findById(id).orElseThrow(() -> new NotFoundException("Tributo não encontrado."));
		return toBuilder.buildTO(entity);
	}

	public Tributos getById(Long id) {
		return repository.findById(id).orElseGet(null);
	}

}
