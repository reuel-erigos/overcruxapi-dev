package br.com.crux.cmd;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.MovimentacoesContabeisTOBuilder;
import br.com.crux.dao.repository.MovimentacoesContabeisRepository;
import br.com.crux.entity.MovimentacoesContabeis;
import br.com.crux.exception.NotFoundException;
import br.com.crux.to.MovimentacoesContabeisTO;

@Component
public class GetMovimentacoesContabeisCmd {

	@Autowired private MovimentacoesContabeisRepository repository;
	@Autowired private MovimentacoesContabeisTOBuilder toBuilder;
	@Autowired private GetUnidadeLogadaCmd getUnidadeLogadaCmd;

	public List<MovimentacoesContabeisTO> getAll() {
		Long idInstituicao = getUnidadeLogadaCmd.getUnidadeTO().getInstituicao().getId();
		Optional<List<MovimentacoesContabeis>> entitys = repository.findByIdInstituicao(idInstituicao);
		if (entitys.isPresent()) {
			return toBuilder.buildAll(entitys.get());
		}
		return new ArrayList<MovimentacoesContabeisTO>();
	}

	public MovimentacoesContabeisTO getTOById(Long id) {
		MovimentacoesContabeis movimentacao = repository.findById(id).orElseThrow(() -> new NotFoundException("Entidade não encontrada"));
		return toBuilder.buildTO(movimentacao);
	}

	public MovimentacoesContabeis getById(Long id) {
		return repository.findById(id).orElseGet(null);
	}
	
	
}
