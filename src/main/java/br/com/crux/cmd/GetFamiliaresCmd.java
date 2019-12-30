package br.com.crux.cmd;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.FamiliaresTOBuilder;
import br.com.crux.dao.repository.FamiliaresRepository;
import br.com.crux.entity.Familiares;
import br.com.crux.exception.NotFoundException;
import br.com.crux.to.FamiliaresTO;

@Component
public class GetFamiliaresCmd {

	@Autowired private FamiliaresRepository repository;
	@Autowired private FamiliaresTOBuilder toBuilder;
	@Autowired private GetUnidadeLogadaCmd getUnidadeLogadaCmd;
	
	public List<FamiliaresTO> getFamiliaresPorAluno(Long idAluno) {
		Optional<List<Familiares>> listaRetorno = repository.findByFamiliaresPorAluno(idAluno);
		if(listaRetorno.isPresent()) {
			return toBuilder.buildAll(listaRetorno.get());
		}
		return new ArrayList<FamiliaresTO>();
	}
	
	public List<FamiliaresTO> getAll() {
		Optional<List<Familiares>> listaRetorno = repository.findByUnidade(getUnidadeLogadaCmd.get().getId());
		if(listaRetorno.isPresent()) {
			return toBuilder.buildAll(listaRetorno.get());
		}
		return new ArrayList<FamiliaresTO>();
	}
	
	public FamiliaresTO getTOById(Long id) {
		Familiares entity = repository.findById(id).orElseThrow(()-> new NotFoundException("Familiar não encontrada."));
		return toBuilder.buildTO(entity);
	}
	
	public Familiares getById(Long id) {
		return repository.findById(id).orElseGet(null);
	}
			
}
