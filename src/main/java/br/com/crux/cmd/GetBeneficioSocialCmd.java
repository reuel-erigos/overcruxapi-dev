package br.com.crux.cmd;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.BeneficioSocialTOBuilder;
import br.com.crux.dao.repository.BeneficioSocialRepository;
import br.com.crux.entity.BeneficioSocial;
import br.com.crux.exception.NotFoundException;
import br.com.crux.to.BeneficioSocialTO;

@Component
public class GetBeneficioSocialCmd {

	@Autowired private BeneficioSocialRepository repository;
	@Autowired private BeneficioSocialTOBuilder toBuilder;

	public List<BeneficioSocialTO> getAll() {
		List<BeneficioSocial> entitys = repository.findAll();
		if (entitys == null || entitys.isEmpty()) {
			return new ArrayList<BeneficioSocialTO>();
		}
		return toBuilder.buildAllTO(entitys);
	}

	public BeneficioSocialTO getTOById(Long id) {
		BeneficioSocial entity = repository.findById(id).orElseThrow(() -> new NotFoundException("Entidade não encontrada."));
		return toBuilder.buildTO(entity);
	}

	public BeneficioSocial getById(Long id) {
		return repository.findById(id).orElseGet(null);
	}

}
