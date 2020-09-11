package br.com.crux.cmd;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.TributoMovimentacaoTOBuilder;
import br.com.crux.dao.repository.TributoMovimentacaoRepository;
import br.com.crux.entity.TributosMovimentacoes;
import br.com.crux.exception.NotFoundException;
import br.com.crux.to.TributosMovimentacoesTO;

@Component
public class GetTributoMovimentacaoCmd {

	@Autowired private TributoMovimentacaoRepository repository;
	@Autowired private TributoMovimentacaoTOBuilder toBuilder;

	
	public List<TributosMovimentacoes> getAllByIdMovimentacao(Long idMovimentacao) {
		Optional<List<TributosMovimentacoes>> entitys = repository.findByIdMovimentacao(idMovimentacao);
		if (entitys.isPresent()) {
			return entitys.get();
		}
		return new ArrayList<TributosMovimentacoes>();
	}

	
	public List<TributosMovimentacoesTO> getAllTOByIdMovimentacao(Long idMovimentacao) {
		Optional<List<TributosMovimentacoes>> entitys = repository.findByIdMovimentacao(idMovimentacao);
		if (entitys.isPresent()) {
			return toBuilder.buildAll(entitys.get());
		}
		return new ArrayList<TributosMovimentacoesTO>();
	}

	public TributosMovimentacoesTO getTOById(Long id) {
		TributosMovimentacoes entity = repository.findById(id).orElseThrow(() -> new NotFoundException("Tributo da movimentação não encontrado."));
		return toBuilder.buildTO(entity);
	}

	public TributosMovimentacoes getById(Long id) {
		return repository.findById(id).orElseGet(null);
	}

}
