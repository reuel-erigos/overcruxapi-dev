package br.com.crux.cmd;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.dao.repository.ProgramaRepository;
import br.com.crux.dao.repository.ProgramasUnidadeRepository;
import br.com.crux.entity.Programa;
import br.com.crux.entity.ProgramasUnidade;
import br.com.crux.exception.NotFoundException;
import br.com.crux.exception.ParametroNaoInformadoException;
import br.com.crux.exception.TabaleReferenciaEncontradaException;

@Component
public class ExcluirProgramaCmd {

	@Autowired private ProgramaRepository repository;
	@Autowired private ProgramasUnidadeRepository programasUnidadeRepository;

	public void excluir(Long id) {

		if (Objects.isNull(id)) {
			throw new ParametroNaoInformadoException("Erro ao excluir, parâmetro ausente.");
		}

		Programa entity = repository.findById(id)
				.orElseThrow(() -> new NotFoundException("Programa informado não existe."));

		if (Objects.nonNull(entity.getIniciativa())) {
			throw new TabaleReferenciaEncontradaException("Por favor, excluir a Iniciativa primeiro!");
		}

		List<ProgramasUnidade> lista = programasUnidadeRepository.findByPrograma(entity)
				.orElse(Collections.emptyList());

		if (!lista.isEmpty()) {
			programasUnidadeRepository.deleteAll(lista);
		}

		repository.deleteById(id);

	}
}
