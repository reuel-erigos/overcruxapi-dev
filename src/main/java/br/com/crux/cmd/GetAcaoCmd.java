package br.com.crux.cmd;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.AcaoTOBuilder;
import br.com.crux.dao.repository.AcaoRepository;
import br.com.crux.entity.Acoes;
import br.com.crux.exception.NotFoundException;
import br.com.crux.to.AcaoTO;

@Component
public class GetAcaoCmd {

	@Autowired private AcaoRepository repository;
	@Autowired private AcaoTOBuilder toBuilder;
	@Autowired private GetUnidadeLogadaCmd getUnidadeLogadaCmd;
	
	
	public List<AcaoTO> getAll() {
		Long idUnidade = getUnidadeLogadaCmd.get().getId();
		Optional<List<Acoes>> entitys = repository.findByUnidade(idUnidade);
		if(entitys.isPresent()) {
			return toBuilder.buildAll(entitys.get());
		}
		return new ArrayList<AcaoTO>();
	}
	
	public AcaoTO getByTOId(Long id) {
		Acoes entity = repository.findById(id).orElseThrow(() -> new NotFoundException("Acao não encontrada."));
		return toBuilder.buildTO(entity);
	}

	public Acoes getById(Long id) {
		return repository.findById(id).orElse(null);
	}
			
}
