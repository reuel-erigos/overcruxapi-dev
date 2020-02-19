package br.com.crux.cmd;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.ItensMovimentacoesTOBuilder;
import br.com.crux.dao.repository.ItensMovimentacoesRepository;
import br.com.crux.entity.ItensMovimentacoes;
import br.com.crux.entity.Movimentacoes;
import br.com.crux.exception.NotFoundException;
import br.com.crux.to.ItensMovimentacoesTO;

@Component
public class GetItensMovimentacoesCmd {

	@Autowired private ItensMovimentacoesTOBuilder toBuilder;
	@Autowired private ItensMovimentacoesRepository repository;
	@Autowired private GetUnidadeLogadaCmd getUnidadeLogadaCmd;

	public ItensMovimentacoesTO getTOById(Long id) {
		Optional<ItensMovimentacoes> entityOptional = repository.findById(id);
		if (!entityOptional.isPresent()) {
			throw new NotFoundException("Item movimentação não encontrado.");
		}
		return toBuilder.buildTO(entityOptional.get());
	}

	public List<ItensMovimentacoesTO> getItensMovimentacoesTOByMovimentacao(Movimentacoes movimentacoes) {
		Optional<List<ItensMovimentacoes>> lista = repository.findByMovimentacao(movimentacoes);

		if (lista.isPresent()) {
			return toBuilder.buildAll(lista.get());
		}

		return Collections.emptyList();

	}

	public List<ItensMovimentacoes> getPorMovimentacoes(Movimentacoes p) {
		return repository.findByMovimentacao(p)
				.orElse(new ArrayList<ItensMovimentacoes>());
	}
	
	public ItensMovimentacoes getById(Long id) {
		return repository.findById(id).orElse(null);
	}

	public List<ItensMovimentacoesTO> getAllCombo() {
		Long idUnidade = getUnidadeLogadaCmd.get().getId();
		List<ItensMovimentacoes> lista = repository.findByUnidade(idUnidade).orElse(Collections.emptyList());
		return toBuilder.buildAllCombo(lista);
	}


}
