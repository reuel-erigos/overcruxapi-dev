package br.com.crux.cmd;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.MetasTOBuilder;
import br.com.crux.dao.repository.MetasRepository;
import br.com.crux.entity.Metas;
import br.com.crux.exception.NotFoundException;
import br.com.crux.to.MetasTO;

@Component
public class GetMetasCmd {

	@Autowired private MetasRepository metasRepository;
	@Autowired private MetasTOBuilder metasTOBuilder;
	@Autowired private GetUnidadeLogadaCmd getUnidadeLogadaCmd;
	
	public List<MetasTO> getAll() {
		Long idUnidade = getUnidadeLogadaCmd.get().getId();
		Optional<List<Metas>> perspectivas = metasRepository.findByIdUnidade(idUnidade);
		if(perspectivas.isPresent()) {
			return metasTOBuilder.buildAll(perspectivas.get());
		}
		return new ArrayList<MetasTO>();
	}
	
	public MetasTO getById(Long id) {
		Optional<Metas> entityOptional = metasRepository.findById(id);
		if(!entityOptional.isPresent()) {
			throw new NotFoundException("Meta não encontrado");
		}
		return metasTOBuilder.buildTO(entityOptional.get());
	}
			
}
