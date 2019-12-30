package br.com.crux.cmd;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.ProgramaTOBuilder;
import br.com.crux.dao.repository.ProgramaRepository;
import br.com.crux.entity.Programa;
import br.com.crux.exception.NotFoundException;
import br.com.crux.to.ProgramaTO;

@Component
public class GetProgramaCmd {

	@Autowired private ProgramaRepository repository;
	@Autowired private ProgramaTOBuilder toBuilder;
	
	@Autowired private GetUnidadeLogadaCmd getUnidadeLogadaCmd;
	
	
	public List<ProgramaTO> getAllProgramasIntituicaoLogada() {
		Long idInstituicao = getUnidadeLogadaCmd.getUnidadeTO().getInstituicao().getId();
		
		Optional<List<Programa>> listaRetorno = repository.findByIdInstituicao(idInstituicao);
		if(listaRetorno.isPresent()) {
			return toBuilder.buildAll(listaRetorno.get());
		}
		return new ArrayList<ProgramaTO>();
	}

	
	public List<ProgramaTO> getAll() {
		Long idUnidade = getUnidadeLogadaCmd.get().getId();
		Optional<List<Programa>> listaRetorno = repository.findByIdUnidade(idUnidade);
		if(listaRetorno.isPresent()) {
			return toBuilder.buildAll(listaRetorno.get());
		}
		return new ArrayList<ProgramaTO>();
	}
	
	public ProgramaTO getTOById(Long id) {
		Programa entity = repository.findById(id).orElseThrow(() -> new NotFoundException("Programa não encontrado."));
		return toBuilder.buildTOComDependencias(entity);
	}
	
	public Programa getById(Long id) {
		return repository.findById(id).orElseGet(null);
	}
			
}
