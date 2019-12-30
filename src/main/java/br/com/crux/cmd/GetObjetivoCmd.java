package br.com.crux.cmd;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.ObjetivoTOBuilder;
import br.com.crux.dao.repository.ObjetivoRepository;
import br.com.crux.entity.Objetivo;
import br.com.crux.exception.NotFoundException;
import br.com.crux.to.ObjetivoTO;

@Component
public class GetObjetivoCmd {

	@Autowired private ObjetivoRepository objetivoRepository;
	@Autowired private ObjetivoTOBuilder objetivoTOBuilder;
	@Autowired private GetUnidadeLogadaCmd getUnidadeLogadaCmd;

	public List<ObjetivoTO> getAll() {
		Long idUnidade = getUnidadeLogadaCmd.get().getId();
		Optional<List<Objetivo>> objetivos = objetivoRepository.findByIdUnidade(idUnidade);

		if (objetivos.isPresent()) {
			return objetivoTOBuilder.buildAll(objetivos.get());
		}
		return new ArrayList<ObjetivoTO>();
	}

	public ObjetivoTO getTOById(Long id) {
		Objetivo entityOptional = objetivoRepository.findById(id).orElseThrow(() -> new NotFoundException("Objetivo não encontrado"));
		return objetivoTOBuilder.buildTO(entityOptional);
	}

	public Objetivo getById(Long id) {
		return objetivoRepository.findById(id).orElseGet(null);
	}

}
