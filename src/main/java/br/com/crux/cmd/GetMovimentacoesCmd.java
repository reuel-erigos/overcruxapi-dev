package br.com.crux.cmd;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.MovimentacoesTOBuilder;
import br.com.crux.dao.repository.MovimentacoesRepository;
import br.com.crux.entity.Movimentacoes;
import br.com.crux.exception.NotFoundException;
import br.com.crux.to.MovimentacoesTO;

@Component
public class GetMovimentacoesCmd {

	@Autowired private MovimentacoesRepository repository;
	@Autowired private MovimentacoesTOBuilder toBuilder;
	@Autowired private GetUnidadeLogadaCmd getUnidadeLogadaCmd;

	public List<MovimentacoesTO> getAll() {
		Long idUnidade = getUnidadeLogadaCmd.get()
				.getId();
		Optional<List<Movimentacoes>> entitys = repository.findByIdUnidade(idUnidade);
		if (entitys.isPresent()) {
			return toBuilder.buildAll(entitys.get());
		}
		return new ArrayList<MovimentacoesTO>();

	}

	public MovimentacoesTO getTOById(Long id) {
		Movimentacoes entity = repository.findById(id)
				.orElseThrow(() -> new NotFoundException("Entidade não encontrada"));
		return toBuilder.buildTO(entity);
	}

	public Movimentacoes getById(Long id) {
		return repository.findById(id)
				.orElseGet(null);
	}

}
