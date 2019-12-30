package br.com.crux.cmd;

import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.UnidadeTOBuilder;
import br.com.crux.dao.repository.UnidadeRepository;
import br.com.crux.entity.Unidade;
import br.com.crux.exception.NotFoundException;
import br.com.crux.exception.ParametroNaoInformadoException;

@Component
public class GetUnidadePorIdCmd {
	
	@Autowired UnidadeRepository unidadeRepository;
	@Autowired UnidadeTOBuilder unidadeBuilder;
	
	
	public Unidade getUnidade(Long idUnidade) {
		if(Objects.isNull(idUnidade)) {
			throw new ParametroNaoInformadoException("A unidade não foi informada.");
		}
		
		Optional<Unidade> unidadeOptional = unidadeRepository.findById(idUnidade);
		if(!unidadeOptional.isPresent()) {
			throw new NotFoundException("Unidade " + idUnidade + "não encontrada");
		}
		
		return unidadeOptional.get();
	}

}
