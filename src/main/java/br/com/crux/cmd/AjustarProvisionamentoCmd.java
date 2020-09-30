package br.com.crux.cmd;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.dao.base.BaseDao;
import br.com.crux.dao.repository.ProvisoesRepository;
import br.com.crux.entity.Provisoes;
import br.com.crux.exception.ConciliacaoNaoGeradoException;
import br.com.crux.to.ProvisionamentoTO;

@Component
public class AjustarProvisionamentoCmd extends BaseDao {

	@Autowired private ProvisoesRepository repository;

	public void ajustar(List<ProvisionamentoTO> param) {
		try {
			param.stream().forEach(c -> {
				Optional<Provisoes> entityOptional = repository.findById(c.getId());
				if(entityOptional.isPresent()) {
					Provisoes entity = entityOptional.get();
					
					entity.setDataExportacao(LocalDateTime.now());
					if(c.getSituacao().equals("G") || c.getSituacao().equals("A")) {
						entity.setStatus("E");
						c.setSituacao("E");
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
