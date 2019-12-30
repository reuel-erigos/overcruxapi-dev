package br.com.crux.cmd;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.ColaboradoresAtividadeTOBuilder;
import br.com.crux.dao.repository.ColaboradoresAtividadeRepository;
import br.com.crux.dao.repository.UnidadeRepository;
import br.com.crux.entity.ColaboradoresAtividade;
import br.com.crux.entity.Unidade;
import br.com.crux.exception.NotFoundException;
import br.com.crux.to.ColaboradoresAtividadeTO;

@Component
public class GetColaboradoresAtividadeCmd {

	@Autowired private ColaboradoresAtividadeRepository repository;
	@Autowired private ColaboradoresAtividadeTOBuilder toBuilder;
	@Autowired private UnidadeRepository unidadeRepository;
	@Autowired private GetUnidadeLogadaCmd getUnidadeLogadaCmd;

	public List<ColaboradoresAtividadeTO> getAll() {
		Optional<Unidade> unidade = unidadeRepository.findById(getUnidadeLogadaCmd.get().getId());
		Optional<List<ColaboradoresAtividade>> listaRetorno = repository.findByIdUnidade(unidade.get().getIdUnidade());
		if (listaRetorno.isPresent()) {
			return toBuilder.buildAll(listaRetorno.get());
		}
		return new ArrayList<ColaboradoresAtividadeTO>();
	}

	public List<ColaboradoresAtividade> getAllPorAtividade(Long idAtividade) {
		return repository.getPorAtividade(idAtividade).orElse(Collections.emptyList());
	}

	public ColaboradoresAtividadeTO getById(Long id) {
		ColaboradoresAtividade entity = repository.findById(id).orElseThrow(() -> new NotFoundException("Colaborador da Atividade não encontrado."));
		return toBuilder.buildTO(entity);
	}

	public List<ColaboradoresAtividadeTO> getPorAtividade(Long id) {
		List<ColaboradoresAtividade> lista = repository.getPorAtividade(id).orElse(Collections.emptyList());
		return toBuilder.buildAll(lista);
	}

}
