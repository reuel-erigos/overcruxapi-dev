package br.com.crux.cmd;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.PlanosAcaoTOBuilder;
import br.com.crux.dao.repository.PlanosAcaoRepository;
import br.com.crux.entity.PlanosAcao;
import br.com.crux.exception.NotFoundException;
import br.com.crux.to.PlanosAcaoTO;

@Component
public class GetPlanosAcaoCmd {

	@Autowired private PlanosAcaoRepository repository;
	@Autowired private PlanosAcaoTOBuilder toBuilder;
	@Autowired private GetUnidadeLogadaCmd getUnidadeLogadaCmd;
	
	public List<PlanosAcaoTO> getAll() {
		Long idUnidade = getUnidadeLogadaCmd.get().getId();
		Optional<List<PlanosAcao>> listaRetorno = repository.findByIdUnidade(idUnidade);
		if(listaRetorno.isPresent()) {
			return toBuilder.buildAll(listaRetorno.get());
		}
		return new ArrayList<PlanosAcaoTO>();
	}
	
	public PlanosAcaoTO getTOById(Long id) {
		PlanosAcao entity = repository.findById(id).orElseThrow(() -> new NotFoundException("Planos de Acao não encontrado."));
		
		return toBuilder.buildTO(entity);
	}
	
	public PlanosAcao getById(Long id) {
		return repository.findById(id).orElseGet(null);
		
	}
			
}
