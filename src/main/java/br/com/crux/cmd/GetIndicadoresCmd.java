package br.com.crux.cmd;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.IndicadoresTOBuilder;
import br.com.crux.dao.repository.IndicadoresRepository;
import br.com.crux.entity.Indicadores;
import br.com.crux.exception.NotFoundException;
import br.com.crux.to.IndicadoresTO;

@Component
public class GetIndicadoresCmd {

	@Autowired private IndicadoresRepository indicadoresRepository;
	@Autowired private IndicadoresTOBuilder indicadoresTOBuilder;
	@Autowired private GetUnidadeLogadaCmd getUnidadeLogadaCmd;
	
	public List<IndicadoresTO> getAll() {
		Long idUnidade = getUnidadeLogadaCmd.get().getId();
		Optional<List<Indicadores>> entitys = indicadoresRepository.findByIdUnidade(idUnidade);
		if(entitys.isPresent()) {
			return indicadoresTOBuilder.buildAll(entitys.get());
		}
		return new ArrayList<IndicadoresTO>();
	}
	
	public IndicadoresTO getTOById(Long id) {
		Indicadores entity = indicadoresRepository.findById(id).orElseThrow(() -> new NotFoundException("Indicador não encontrado"));
	
		return indicadoresTOBuilder.buildTO(entity);
	}

	public Indicadores getById(Long id) {
		return indicadoresRepository.findById(id).orElseGet(null);
		
	}
			
}
