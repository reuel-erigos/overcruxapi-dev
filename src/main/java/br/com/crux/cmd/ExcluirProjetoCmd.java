package br.com.crux.cmd;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.dao.repository.ProjetoRepository;
import br.com.crux.dao.repository.ProjetosUnidadeRepository;
import br.com.crux.entity.Projeto;
import br.com.crux.entity.ProjetosUnidade;
import br.com.crux.exception.NotFoundException;
import br.com.crux.exception.ParametroNaoInformadoException;

@Component
public class ExcluirProjetoCmd {

	@Autowired private ProjetoRepository repository;
	@Autowired private ProjetosUnidadeRepository projetosUnidadeRepository;

	public void excluir(Long id) {
		if (Objects.isNull(id)) {
			throw new ParametroNaoInformadoException("Erro ao excluir, parâmetro ausente.");
		}

		Projeto entity = repository.findById(id)
				.orElseThrow(() -> new NotFoundException("Projeto informado não existe."));

		List<ProjetosUnidade> lista = projetosUnidadeRepository.findByProjeto(entity)
				.orElse(Collections.emptyList());

		if (!lista.isEmpty()) {
			projetosUnidadeRepository.deleteAll(lista);
		}

		repository.deleteById(id);

	}
}
