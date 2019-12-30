package br.com.crux.cmd;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.TurmasTOBuilder;
import br.com.crux.dao.repository.TurmasRepository;
import br.com.crux.entity.Turmas;
import br.com.crux.exception.NotFoundException;
import br.com.crux.to.TurmasTO;

@Component
public class GetTurmasCmd {

	@Autowired private TurmasRepository repository;
	@Autowired private TurmasTOBuilder toBuilder;
	
	@Autowired private GetUnidadeLogadaCmd getUnidadeLogadaCmd;
	
	
	public List<TurmasTO> getAllFilter(Long idPrograma, Long idProjeto, Long idUnidade) {
		Long idInstituicao = getUnidadeLogadaCmd.getUnidadeTO().getInstituicao().getId();
		
		Optional<List<Turmas>> entitys = Optional.empty();
		
		if(Objects.isNull(idPrograma) && Objects.isNull(idProjeto) && Objects.isNull(idUnidade) ) {
			entitys = repository.findAllByIdInstituicao(idInstituicao);	
		}else if(Objects.nonNull(idPrograma) && Objects.isNull(idProjeto) && Objects.isNull(idUnidade) ) {
			entitys = repository.findAllByIdPrograma(idPrograma);
		}else if(Objects.isNull(idPrograma) && Objects.nonNull(idProjeto) && Objects.isNull(idUnidade) ) {
			entitys = repository.findAllByIdProjeto(idProjeto);
		}else if(Objects.isNull(idPrograma) && Objects.isNull(idProjeto) && Objects.nonNull(idUnidade) ) {
			entitys = repository.findAllByIdUnidade(idUnidade);
		}else if(Objects.nonNull(idPrograma) && Objects.nonNull(idProjeto) && Objects.isNull(idUnidade) ) {
			entitys = repository.findAllByIdProjetoAndIdPrograma(idPrograma, idProjeto);
		}else if(Objects.isNull(idPrograma) && Objects.nonNull(idProjeto) && Objects.nonNull(idUnidade) ) {
			entitys = repository.findAllByIdProjetoAndIdUnidade(idProjeto, idUnidade);
		}else if(Objects.nonNull(idPrograma) && Objects.isNull(idProjeto) && Objects.nonNull(idUnidade) ) {
			entitys = repository.findAllByIdProgramaAndIdUnidade(idPrograma, idUnidade);
		}
		
		if(entitys.isPresent()) {
			return toBuilder.buildAll(entitys.get());
		}
		
		return new ArrayList<TurmasTO>();
	}

	public TurmasTO getTOById(Long id) {
		Turmas entity = repository.findById(id).orElseThrow(() -> new NotFoundException("Turma não encontrada."));
		return toBuilder.buildTO(entity);
	}
	
	public Turmas getById(Long id) {
		return repository.findById(id).orElseGet(null);
	}
			
}
