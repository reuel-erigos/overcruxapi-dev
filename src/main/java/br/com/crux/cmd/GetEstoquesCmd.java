package br.com.crux.cmd;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.EstoquesTOBuilder;
import br.com.crux.dao.repository.EstoquesRepository;
import br.com.crux.entity.Estoques;
import br.com.crux.exception.NotFoundException;
import br.com.crux.to.EstoquesTO;

@Component
public class GetEstoquesCmd {

	@Autowired private EstoquesRepository repository;
	@Autowired private EstoquesTOBuilder toBuilder;
	@Autowired private GetUnidadeLogadaCmd getUnidadeLogadaCmd;

	
	public List<EstoquesTO> getAllFilter(Long idMaterial, Long idPrograma, Long idProjeto) {
		Long idUnidade = getUnidadeLogadaCmd.get().getId();
		
		Optional<List<Estoques>> entitys = Optional.empty();
		
		if(Objects.isNull(idMaterial) && Objects.isNull(idPrograma) && Objects.isNull(idProjeto)) {
			entitys = repository.findByUnidade(idUnidade);
			
		} else if(Objects.nonNull(idMaterial) && Objects.nonNull(idPrograma) && Objects.nonNull(idProjeto)) {
			entitys = repository.findByProjetoAndProgramaAndMaterial(idProjeto, idPrograma, idMaterial);	
		
		} else if(Objects.nonNull(idMaterial) && Objects.isNull(idPrograma) && Objects.isNull(idProjeto)) {
			entitys = repository.findByMaterial(idMaterial);
		
		} else if(Objects.nonNull(idMaterial) && Objects.nonNull(idPrograma)  && Objects.isNull(idProjeto)) {
			entitys = repository.findByProgramaAndMaterial(idPrograma, idMaterial);
			
		} else if(Objects.nonNull(idMaterial) && Objects.isNull(idPrograma)  && Objects.nonNull(idProjeto)) {
			entitys = repository.findByProjetoAndMaterial(idProjeto, idMaterial);
			
		} else if(Objects.isNull(idMaterial) && Objects.nonNull(idPrograma)  && Objects.isNull(idProjeto)) {
			entitys = repository.findByPrograma(idPrograma);

		} else if(Objects.isNull(idMaterial) && Objects.isNull(idPrograma)  && Objects.nonNull(idProjeto)) {
			entitys = repository.findByProjeto(idProjeto);
		
		} else if(Objects.isNull(idMaterial) && Objects.nonNull(idPrograma)  && Objects.nonNull(idProjeto)) {
			entitys = repository.findByProjetoAndPrograma(idProjeto, idPrograma);
		}
		
		if(entitys.isPresent()) {
			return toBuilder.buildAll(entitys.get());
		}
		
		return new ArrayList<EstoquesTO>();
	}

	public EstoquesTO getTOById(Long id) {
		Estoques entity = repository.findById(id).orElseThrow(() -> new NotFoundException("Estoque não encontrado."));
		return toBuilder.buildTO(entity);
	}

	public Estoques getById(Long id) {
		return repository.findById(id).orElseGet(null);
	}

	public List<EstoquesTO> getAllCombo() {
		Long idUnidade = getUnidadeLogadaCmd.get().getId();
		List<Estoques> lista = repository.findByUnidade(idUnidade).orElse(Collections.emptyList());
		return toBuilder.buildAllCombo(lista);
	}

}
