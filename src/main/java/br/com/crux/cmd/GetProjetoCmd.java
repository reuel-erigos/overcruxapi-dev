package br.com.crux.cmd;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.ProjetoTOBuilder;
import br.com.crux.dao.repository.ProjetoRepository;
import br.com.crux.entity.Projeto;
import br.com.crux.exception.NotFoundException;
import br.com.crux.to.ProjetoTO;

@Component
public class GetProjetoCmd {

	@Autowired private ProjetoRepository repository;
	@Autowired private ProjetoTOBuilder toBuilder;
	
	@Autowired private GetUnidadeLogadaCmd getUnidadeLogadaCmd;
	
	
	public List<ProjetoTO> getAllPrograma(Long idPrograma) {
		Optional<List<Projeto>> listaRetorno = repository.findByIdPrograma(idPrograma);
		if(listaRetorno.isPresent()) {
			return toBuilder.buildAll(listaRetorno.get());
		}
		return new ArrayList<ProjetoTO>();
	}	
	
	public List<ProjetoTO> getAllIntituicaoLogada() {
		Long idInstituicao = getUnidadeLogadaCmd.getUnidadeTO().getInstituicao().getId();
		Optional<List<Projeto>> listaRetorno = repository.findByIdInstituicao(idInstituicao);
		if(listaRetorno.isPresent()) {
			return toBuilder.buildAll(listaRetorno.get());
		}
		return new ArrayList<ProjetoTO>();
	}
	
	public List<ProjetoTO> getAll() {
		Long idUnidade = getUnidadeLogadaCmd.get().getId();
		Optional<List<Projeto>> listaRetorno = repository.findByIdUnidade(idUnidade);
		if(listaRetorno.isPresent()) {
			return toBuilder.buildAll(listaRetorno.get());
		}
		return new ArrayList<ProjetoTO>();
	}
	
	public ProjetoTO getTOById(Long id) {
		Projeto entity = repository.findById(id).orElseThrow(()-> new NotFoundException("Projeto não encontrado.") );
		return toBuilder.buildTO(entity);
	}

	public Projeto getById(Long id) {
		return repository.findById(id).orElseGet(null);
	}
			
}
