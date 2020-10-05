package br.com.crux.cmd;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.RateiosProgramaProjetoTOBuilder;
import br.com.crux.dao.repository.VWRateiosProgramaProjetoRepository;
import br.com.crux.entity.view.RateiosProgramaProjeto;
import br.com.crux.to.RateiosProgramaProjetoTO;

@Component
public class GetRateiosProgramaProjetoCmd {
	
	@Autowired private VWRateiosProgramaProjetoRepository repository;
	@Autowired private RateiosProgramaProjetoTOBuilder toBuilder;
	
	public List<RateiosProgramaProjetoTO> getAllPorIdMovimentacao(Long idMovimentacao){
		Optional<List<RateiosProgramaProjeto>> lista = repository.getAllPorIdMovimentacao(idMovimentacao);
		if(lista.isPresent()) {
			return toBuilder.buildAll(lista.get());
		}
		
		return null;
	}

	
	
}
