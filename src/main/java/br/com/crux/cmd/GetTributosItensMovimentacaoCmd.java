package br.com.crux.cmd;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.TributosItensMovimentacaoTOBuilder;
import br.com.crux.dao.repository.TributosItensMovimentacaoRepository;
import br.com.crux.entity.TributosItensMovimentacoes;
import br.com.crux.exception.NotFoundException;
import br.com.crux.to.TributosItensMovimentacoesTO;

@Component
public class GetTributosItensMovimentacaoCmd {

	@Autowired private TributosItensMovimentacaoRepository repository;
	@Autowired private TributosItensMovimentacaoTOBuilder toBuilder;

	
	public List<TributosItensMovimentacoes> getAllByIdItemMovimentacao(Long idItemMovimentacao) {
		Optional<List<TributosItensMovimentacoes>> entitys = repository.findAllByIdItemMovimentacao(idItemMovimentacao);
		if (entitys.isPresent()) {
			return entitys.get();
		}
		return new ArrayList<TributosItensMovimentacoes>();
	}

	public TributosItensMovimentacoes getByIdItemMovimentacaoAndIdTributo(Long idItemMovimentacao, Long idTributo) {
		Optional<TributosItensMovimentacoes> entity = repository.findByIdItemMovimentacaoAndIdTributo(idItemMovimentacao, idTributo);
		if (entity.isPresent()) {
			return entity.get();
		}
		return null;
	}
	
	public TributosItensMovimentacoesTO getTOByIdItemMovimentacaoAndIdTributo(Long idItemMovimentacao, Long idTributo) {
		Optional<TributosItensMovimentacoes> entity = repository.findByIdItemMovimentacaoAndIdTributo(idItemMovimentacao, idTributo);
		if (entity.isPresent()) {
			return toBuilder.buildTO(entity.get());
		}
		return new TributosItensMovimentacoesTO();
	}
	
	public List<TributosItensMovimentacoesTO> getAllTOByIdItemMovimentacao(Long idItemMovimentacao) {
		Optional<List<TributosItensMovimentacoes>> entitys = repository.findAllByIdItemMovimentacao(idItemMovimentacao);
		if (entitys.isPresent()) {
			return toBuilder.buildAll(entitys.get());
		}
		return new ArrayList<TributosItensMovimentacoesTO>();
	}

	public TributosItensMovimentacoesTO getTOById(Long id) {
		TributosItensMovimentacoes entity = repository.findById(id).orElseThrow(() -> new NotFoundException("Tributo do item da movimentação não encontrado."));
		return toBuilder.buildTO(entity);
	}

	public TributosItensMovimentacoes getById(Long id) {
		return repository.findById(id).orElseGet(null);
	}

}
