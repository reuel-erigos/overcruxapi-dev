package br.com.crux.cmd;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.PerspectivaTOBuilder;
import br.com.crux.dao.repository.PerspectivaRepository;
import br.com.crux.entity.Perspectiva;
import br.com.crux.exception.NotFoundException;
import br.com.crux.to.PerspectivaTO;

@Component
public class GetPerspectivasCmd {

	@Autowired private PerspectivaRepository perspectivaRepository;
	@Autowired private PerspectivaTOBuilder perspectivaTOBuilder;
	@Autowired private GetUnidadeLogadaCmd getUnidadeLogadaCmd;
	
	public List<PerspectivaTO> getAll() {
		Long idUnidade = getUnidadeLogadaCmd.get().getId();
		Optional<List<Perspectiva>> entitys = perspectivaRepository.findByIdUnidade(idUnidade);
		if(entitys.isPresent()) {
			return perspectivaTOBuilder.buildAll(entitys.get());
		}
		return new ArrayList<PerspectivaTO>();
	}
	
	public PerspectivaTO getById(Long id) {
		Optional<Perspectiva> entityOptional = perspectivaRepository.findById(id);
		if(!entityOptional.isPresent()) {
			throw new NotFoundException("Perspectiva não encontrado");
		}
		return perspectivaTOBuilder.buildTO(entityOptional.get());
	}
			
}
