package br.com.crux.cmd;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.AcoesCompetenciaTOBuilder;
import br.com.crux.dao.repository.AcoesCompetenciaRepository;
import br.com.crux.entity.AcoesCompetencia;
import br.com.crux.exception.NotFoundException;
import br.com.crux.to.AcoesCompetenciaTO;

@Component
public class GetAcoesCompetenciaCmd {

	@Autowired private AcoesCompetenciaRepository repository;
	@Autowired private AcoesCompetenciaTOBuilder toBuilder;

	public List<AcoesCompetenciaTO> getAllPorUnidadeLogada() {
		List<AcoesCompetenciaTO> lista = toBuilder.buildAll(repository.findAll());

		if (lista == null || lista.isEmpty()) {
			return new ArrayList<AcoesCompetenciaTO>();
		}

		return lista;
	}

	public AcoesCompetenciaTO getTOById(Long id) {
		AcoesCompetencia entity = repository.findById(id).orElseThrow(() -> new NotFoundException("Ações das competências do talento do Funcionario não encontrado."));
		return toBuilder.buildTO(entity);
	}

	public List<AcoesCompetenciaTO> getPorPessoa(Long id) {
		List<AcoesCompetencia> lista = repository.getPorPessoa(id).orElseThrow(() -> new NotFoundException("Ações das competências do talento do Funcionario não encontrado."));
		return toBuilder.buildAll(lista);
	}

}
