package br.com.crux.cmd;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.ContasBancariaTOBuilder;
import br.com.crux.dao.repository.ContasBancariaRepository;
import br.com.crux.entity.ContasBancaria;
import br.com.crux.exception.NotFoundException;
import br.com.crux.to.ContasBancariaTO;

@Component
public class GetContasBancariaCmd {

	@Autowired private ContasBancariaRepository repository;
	@Autowired private ContasBancariaTOBuilder toBuilder;
	@Autowired private GetUnidadeLogadaCmd getUnidadeLogadaCmd;

	public List<ContasBancariaTO> getAll() {

		Long idInstituicao = getUnidadeLogadaCmd.getUnidadeTO().getInstituicao().getId();
		Optional<List<ContasBancaria>> entitys = repository.findByIdInstituicao(idInstituicao);
		if (entitys.isPresent()) {
			return toBuilder.buildAll(entitys.get());
		}
		return new ArrayList<ContasBancariaTO>();

	}

	public ContasBancariaTO getTOById(Long id) {
		ContasBancaria contasBancaria = repository.findById(id)
				.orElseThrow(() -> new NotFoundException("Entidade não encontrada"));
		return toBuilder.buildTO(contasBancaria);
	}

	public ContasBancaria getById(Long id) {
		return repository.findById(id)
				.orElseGet(null);
	}

	public List<ContasBancariaTO> getAllComboByUnidadeLogada() {
		Long idUnidade = getUnidadeLogadaCmd.get().getId();
		Optional<List<ContasBancaria>> entitys = repository.findByIdUnidade(idUnidade);
		if (entitys.isPresent()) {
			return toBuilder.buildAllCombo(entitys.get());
		}
		return new ArrayList<ContasBancariaTO>();
	}

	public List<ContasBancariaTO> getAllComboByInstituicaoLogada() {
		Long idInstituicao = getUnidadeLogadaCmd.getUnidadeTO().getInstituicao().getId();
		Optional<List<ContasBancaria>> entitys = repository.findByIdInstituicao(idInstituicao);
		if (entitys.isPresent()) {
			return toBuilder.buildAllCombo(entitys.get());
		}
		return new ArrayList<ContasBancariaTO>();
	}
	
}
