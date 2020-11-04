package br.com.crux.cmd;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.CategoriasContabeisTOBuilder;
import br.com.crux.builder.PlanosContasTOBuilder;
import br.com.crux.dao.repository.CategoriasContabeisRepository;
import br.com.crux.dao.repository.VWPlanosContasRepository;
import br.com.crux.entity.CategoriasContabeis;
import br.com.crux.entity.view.PlanosContas;
import br.com.crux.exception.NotFoundException;
import br.com.crux.to.CategoriasContabeisTO;
import br.com.crux.to.PlanosContasTO;

@Component
public class GetCategoriasContabeisCmd {

	@Autowired private CategoriasContabeisRepository repository;
	@Autowired private VWPlanosContasRepository planosContasRepository;
	@Autowired private CategoriasContabeisTOBuilder toBuilder;
	@Autowired private PlanosContasTOBuilder planosContasTOBuilder;
	@Autowired private GetUnidadeLogadaCmd getUnidadeLogadaCmd;

	public List<CategoriasContabeisTO> getAllByInstituicaoLogada() {
		Long idInstituicao = getUnidadeLogadaCmd.getUnidadeTO().getInstituicao().getId();
		
		Optional<List<CategoriasContabeis>> entitys = repository.findAllByInstituicao(idInstituicao);
		if (entitys.isPresent()) {
			return toBuilder.buildAll(entitys.get());
		}
		return new ArrayList<CategoriasContabeisTO>();

	}

	public List<PlanosContasTO> getAllByInstituicaoLogadaComboSuperior() {
		Long idInstituicao = getUnidadeLogadaCmd.getUnidadeTO().getInstituicao().getId();
		
		Optional<List<PlanosContas>> entitys = planosContasRepository.findAllByInstituicao(idInstituicao);
		if (entitys.isPresent()) {
			return planosContasTOBuilder.buildAll(entitys.get());
		}
		return new ArrayList<PlanosContasTO>();
		
	}

	public CategoriasContabeisTO getTOById(Long id) {
		CategoriasContabeis to = repository.findById(id).orElseThrow(() -> new NotFoundException("Entidade não encontrada"));
		return toBuilder.buildTO(to);
	}

	public CategoriasContabeis getById(Long id) {
		return repository.findById(id).orElseGet(null);
	}

	public List<CategoriasContabeisTO> getAllCombo() {
		Long idInstituicao = getUnidadeLogadaCmd.getUnidadeTO().getInstituicao().getId();
		Optional<List<CategoriasContabeis>> entitys = repository.findAllByInstituicao(idInstituicao);
		if (entitys.isPresent()) {
			return toBuilder.buildAllCombo(entitys.get());
		}
		return new ArrayList<CategoriasContabeisTO>();
	}

}
