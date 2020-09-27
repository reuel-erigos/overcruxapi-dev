package br.com.crux.cmd;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.dao.repository.ConciliacaoBancariaRepository;
import br.com.crux.entity.ConciliacaoBancaria;
import br.com.crux.exception.ConciliacaoNaoGeradoException;
import br.com.crux.to.ConciliacaoTO;

@Component
public class AjustarConciliacaoCmd {

	@Autowired private ConciliacaoBancariaRepository repository;

	public void ajustar(List<ConciliacaoTO> param) {
		try {
			param.stream().forEach(c -> {
				Optional<ConciliacaoBancaria> entityOptional = repository.findById(c.getId());
				if(entityOptional.isPresent()) {
					ConciliacaoBancaria entity = entityOptional.get();
					
					entity.setDataExportacao(LocalDateTime.now());
					if(entity.getStatus().equals("G") || entity.getStatus().equals("A")) {
						entity.setStatus("E");
						repository.save(entity);
					}

					if(entity.getStatus().equals("D")) {
						repository.delete(entity);
					}
				}
			});
			
		} catch (Exception e) {
			throw new ConciliacaoNaoGeradoException(e.getMessage());
		}
	}


}
