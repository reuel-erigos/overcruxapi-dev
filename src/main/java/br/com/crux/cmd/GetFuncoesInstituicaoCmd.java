package br.com.crux.cmd;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.FuncoesInstituicaoTOBuilder;
import br.com.crux.dao.repository.FuncoesInstituicaoRepository;
import br.com.crux.entity.FuncoesInstituicao;
import br.com.crux.entity.Instituicao;
import br.com.crux.exception.NotFoundException;
import br.com.crux.to.FuncoesInstituicaoTO;

@Component
public class GetFuncoesInstituicaoCmd {

	@Autowired private FuncoesInstituicaoTOBuilder toBuilder;
	@Autowired private FuncoesInstituicaoRepository repository;

	public FuncoesInstituicaoTO getById(Long id) {
		Optional<FuncoesInstituicao> entityOptional = repository.findById(id);
		if (!entityOptional.isPresent()) {
			throw new NotFoundException("Funçao da Instituição não encontrada.");
		}
		return toBuilder.buildTO(entityOptional.get());
	}

	public List<FuncoesInstituicaoTO> getFuncoesInstituicaoTOByInstituicao(Instituicao instituicao) {
		Optional<List<FuncoesInstituicao>> lista = repository.findByInstituicao(instituicao);

		if (lista.isPresent()) {
			return toBuilder.buildAll(lista.get());
		}

		return Collections.emptyList();

	}

	public List<FuncoesInstituicao> getPorInstituicao(Instituicao p) {
		return repository.findByInstituicao(p)
				.orElse(new ArrayList<FuncoesInstituicao>());
	}

}
