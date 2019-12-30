package br.com.crux.cmd;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.SituacoesVulnerabilidadeTOBuilder;
import br.com.crux.dao.repository.SituacoesVulnerabilidadeRepository;
import br.com.crux.entity.SituacoesVulnerabilidade;
import br.com.crux.exception.NotFoundException;
import br.com.crux.to.SituacoesVulnerabilidadeTO;

@Component
public class GetSituacoesVulnerabilidadeCmd {

	@Autowired private SituacoesVulnerabilidadeRepository repository;
	@Autowired private SituacoesVulnerabilidadeTOBuilder toBuilder;
	
	public List<SituacoesVulnerabilidadeTO> getAll() {
		List<SituacoesVulnerabilidadeTO> entitys = toBuilder.buildAll(repository.findAll());
		if(entitys == null || entitys.isEmpty()) {
			return new ArrayList<SituacoesVulnerabilidadeTO>();
		}
		return entitys;
	}
	
	public SituacoesVulnerabilidadeTO getById(Long id) {
		Optional<SituacoesVulnerabilidade> entityOptional = repository.findById(id);
		if(!entityOptional.isPresent()) {
			throw new NotFoundException("Situacoes de vulnerabilidade não encontrada.");
		}
		return toBuilder.buildTO(entityOptional.get());
	}
			
}
