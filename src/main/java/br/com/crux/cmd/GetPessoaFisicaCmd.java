package br.com.crux.cmd;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.PessoaFisicaTOBuilder;
import br.com.crux.dao.repository.PessoaFisicaRepository;
import br.com.crux.entity.PessoaFisica;
import br.com.crux.exception.NotFoundException;
import br.com.crux.to.PessoaFisicaTO;

@Component
public class GetPessoaFisicaCmd {

	@Autowired private PessoaFisicaRepository repository;
	@Autowired private PessoaFisicaTOBuilder toBuilder;

	public List<PessoaFisicaTO> getAll() {
		List<PessoaFisica> entitys = repository.findAll();
		if (entitys == null || entitys.isEmpty()) {
			return new ArrayList<PessoaFisicaTO>();
		}
		return toBuilder.buildAll(entitys);
	}

	public PessoaFisicaTO getTOById(Long id) {
		PessoaFisica entity = repository.findById(id).orElseThrow(() -> new NotFoundException("Pessoa física não encontrado."));
		return toBuilder.buildTO(entity);
	}

	public PessoaFisica getById(Long id) {
		return repository.findById(id).orElseGet(null);
	}

}
