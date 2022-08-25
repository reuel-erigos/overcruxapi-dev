package br.com.crux.cmd;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.com.crux.builder.RegiaoAdministrativaTOBuilder;
import br.com.crux.dao.repository.RegiaoAdministrativaRepository;
import br.com.crux.dao.spec.RegiaoAdministrativaSpec;
import br.com.crux.entity.RegiaoAdministrativa;
import br.com.crux.exception.NotFoundException;
import br.com.crux.to.RegiaoAdministrativaTO;
import br.com.crux.to.filtro.FiltroRegiaoAdministrativaTO;

@Component
public class GetRegiaoAdministrativaCmd {

	@Autowired private RegiaoAdministrativaRepository repository;
	@Autowired private RegiaoAdministrativaTOBuilder toBuilder;

	public List<RegiaoAdministrativaTO> getAll() {
		List<RegiaoAdministrativaTO> entitys = toBuilder.buildAll(repository.findAll());
		if (entitys == null || entitys.isEmpty()) {
			return new ArrayList<RegiaoAdministrativaTO>();
		}
		return entitys;
	}

	public RegiaoAdministrativaTO getTOById(Long id) {
		RegiaoAdministrativa entity = repository.findById(id)
				.orElseThrow(() -> new NotFoundException("RegiaoAdministrativa não encontrado."));
		return toBuilder.buildTO(entity);
	}

	public RegiaoAdministrativa getById(Long id) {
		return repository.findById(id)
				.orElseGet(null);
	}
	
	@Transactional(readOnly = true)
	public Page<RegiaoAdministrativaTO> listFilteredAndPaged(FiltroRegiaoAdministrativaTO filtro, Pageable pageable) {
		Page<RegiaoAdministrativa> pageData = repository.findAll(RegiaoAdministrativaSpec.findByCriteria(filtro), pageable);
		final List<RegiaoAdministrativaTO> listTO = new ArrayList<RegiaoAdministrativaTO>();
		pageData.getContent().forEach(regiao -> listTO.add(toBuilder.buildTO(regiao)));
		final Page<RegiaoAdministrativaTO> pageDataTO = new PageImpl<RegiaoAdministrativaTO>(listTO, pageable, pageData.getTotalElements());
		return pageDataTO;
	}


}
