package br.com.crux.cmd;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.ParticipantesAtendimentosTOBuilder;
import br.com.crux.dao.repository.ParticipantesAtendimentosRepository;
import br.com.crux.entity.ParticipantesAtendimentos;
import br.com.crux.exception.NotFoundException;
import br.com.crux.to.ParticipantesAtendimentosTO;

@Component
public class GetParticipantesAtendimentosCmd {

	@Autowired private ParticipantesAtendimentosRepository repository;
	@Autowired private ParticipantesAtendimentosTOBuilder toBuilder;
	@Autowired private GetUnidadeLogadaCmd getUnidadeLogadaCmd;
	
	
	public List<ParticipantesAtendimentosTO> getAll() {
		Long idUnidade = getUnidadeLogadaCmd.get().getId();
		Optional<List<ParticipantesAtendimentos>> entitys = repository.findByUnidade(idUnidade);
		if(entitys.isPresent()) {
			return toBuilder.buildAll(entitys.get());
		}
		return new ArrayList<ParticipantesAtendimentosTO>();
	}
	
	public ParticipantesAtendimentosTO getById(Long id) {
		Optional<ParticipantesAtendimentos> entityOptional = repository.findById(id);
		if(!entityOptional.isPresent()) {
			throw new NotFoundException("Participante atendimento não encontrado.");
		}
		return toBuilder.buildTO(entityOptional.get());
	}
			
}
