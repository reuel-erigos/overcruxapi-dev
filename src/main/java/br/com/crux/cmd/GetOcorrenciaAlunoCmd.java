package br.com.crux.cmd;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.OcorrenciaAlunoTOBuilder;
import br.com.crux.dao.repository.OcorrenciaAlunoRepository;
import br.com.crux.entity.OcorrenciaAluno;
import br.com.crux.exception.NotFoundException;
import br.com.crux.to.OcorrenciaAlunoTO;

@Component
public class GetOcorrenciaAlunoCmd {

	@Autowired private OcorrenciaAlunoRepository repository;
	@Autowired private OcorrenciaAlunoTOBuilder toBuilder;
	@Autowired private GetUnidadeLogadaCmd getUnidadeLogadaCmd;
	
	private Optional<List<OcorrenciaAluno>> getAllUnidadeLogada() {
		Optional<List<OcorrenciaAluno>> entitys = repository.findAllByUnidade(getUnidadeLogadaCmd.get().getId());
		return entitys;
	}
	
	public List<OcorrenciaAlunoTO> getAllFilter(Long idTipoOcorrencia, Long idAluno) {
		Optional<List<OcorrenciaAluno>> entitys = Optional.empty();
		
		if(Objects.isNull(idAluno) && Objects.isNull(idTipoOcorrencia)) {
			entitys = getAllUnidadeLogada();	
		}else if(Objects.isNull(idAluno) && Objects.nonNull(idTipoOcorrencia)) {
			entitys = repository.findAllByTipoOcorrencia(idTipoOcorrencia);
		}else if(Objects.isNull(idTipoOcorrencia)  && Objects.nonNull(idAluno)) {
			entitys = repository.findAllByAluno(idAluno);
		}else if(Objects.nonNull(idAluno) && Objects.nonNull(idTipoOcorrencia)) {
			entitys = repository.findAllByAlunoAndTipoOcorrencia(idAluno, idTipoOcorrencia);
		}
		
		if(entitys.isPresent()) {
			return toBuilder.buildAll(entitys.get());
		}
		
		return new ArrayList<OcorrenciaAlunoTO>();
	}	
	
	public OcorrenciaAlunoTO getById(Long id) {
		Optional<OcorrenciaAluno> entityOptional = repository.findById(id);
		if(!entityOptional.isPresent()) {
			throw new NotFoundException("Ocorrência do aluno não encontrada.");
		}
		return toBuilder.buildTO(entityOptional.get());
	}
			
}
