package br.com.crux.cmd;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.ItensMovimentacoesMateriaisTOBuilder;
import br.com.crux.dao.repository.ItensMovimentacoesMateriaisRepository;
import br.com.crux.entity.ItensMovimentacoesMateriais;
import br.com.crux.entity.MovimentacoesMateriais;
import br.com.crux.exception.NotFoundException;
import br.com.crux.to.ItensMovimentacoesMateriaisTO;

@Component
public class GetItensMovimentacoesMateriaisCmd {

	@Autowired private ItensMovimentacoesMateriaisTOBuilder toBuilder;
	@Autowired private ItensMovimentacoesMateriaisRepository repository;

	public ItensMovimentacoesMateriaisTO getTOById(Long id) {
		Optional<ItensMovimentacoesMateriais> entityOptional = repository.findById(id);
		if (!entityOptional.isPresent()) {
			throw new NotFoundException("Item movimentação não encontrado.");
		}
		return toBuilder.buildTO(entityOptional.get());
	}

	public List<ItensMovimentacoesMateriaisTO> getItensMovimentacoesMateriaisTOByMovimentacaoMateriais(MovimentacoesMateriais movimentacoesMateriais) {
		Optional<List<ItensMovimentacoesMateriais>> lista = repository.findByMovimentacoesMateriais(movimentacoesMateriais);

		if (lista.isPresent()) {
			return toBuilder.buildAll(lista.get());
		}

		return Collections.emptyList();

	}

	public List<ItensMovimentacoesMateriais> getPorMovimentacoesMateriais(MovimentacoesMateriais p) {
		return repository.findByMovimentacoesMateriais(p)
				.orElse(new ArrayList<ItensMovimentacoesMateriais>());
	}

	public ItensMovimentacoesMateriais getById(Long id) {
		return repository.findById(id)
				.orElse(null);
	}

}
