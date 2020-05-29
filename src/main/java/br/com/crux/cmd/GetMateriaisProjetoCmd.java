package br.com.crux.cmd;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.MateriaisProjetoTOBuilder;
import br.com.crux.dao.repository.MateriaisProjetoRepository;
import br.com.crux.dao.repository.ProjetoRepository;
import br.com.crux.entity.MateriaisProjeto;
import br.com.crux.entity.Projeto;
import br.com.crux.exception.base.NegocioException;
import br.com.crux.to.MateriaisProjetoTO;

@Component
public class GetMateriaisProjetoCmd {

	@Autowired private MateriaisProjetoRepository repository;
	@Autowired private MateriaisProjetoTOBuilder toBuilder;
	@Autowired private ProjetoRepository projetoRepository;

	public List<MateriaisProjetoTO> getMateriaisProjetoByProjeto(Projeto projeto) {
		Optional<List<MateriaisProjeto>> lista = repository.findByProjeto(projeto);
		
		if (lista.isPresent()) {
			return toBuilder.buildAllTO(lista.get());
		}
		
		return Collections.emptyList();

	}

	public List<MateriaisProjetoTO> getMateriaisProjetoByIdProjeto(Long idProjeto) {
		Projeto projeto = projetoRepository.findById(idProjeto).orElseThrow(() -> new NegocioException("Projeto não encontrado"));
		return getMateriaisProjetoByProjeto(projeto);

	}

}
