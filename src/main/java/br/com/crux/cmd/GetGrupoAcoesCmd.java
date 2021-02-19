package br.com.crux.cmd;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.GrupoAcoesTOBuilder;
import br.com.crux.dao.repository.GrupoAcoesRepository;
import br.com.crux.entity.GrupoAcoes;
import br.com.crux.to.GrupoAcoesTO;

@Component
public class GetGrupoAcoesCmd {

	@Autowired private GrupoAcoesRepository repository;
	@Autowired private GrupoAcoesTOBuilder toBuilder;

	public GrupoAcoesTO getByNumero(String numero) {
		Optional<GrupoAcoes> entity = repository.findByNumero(numero);
		if(entity.isPresent()) {
			return toBuilder.buildTO(entity.get());
		}
		return null;
	}
	
	public GrupoAcoesTO getByTOId(Long id) {
		Optional<GrupoAcoes> entity = repository.findById(id);
		if(entity.isPresent()) {
			return toBuilder.buildTO(entity.get());
		}
		return null;
	}

	public GrupoAcoes getById(Long id) {
		return repository.findById(id).orElse(null);
	}

}
