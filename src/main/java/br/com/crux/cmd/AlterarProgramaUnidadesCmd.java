package br.com.crux.cmd;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.BiPredicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.ProgramasUnidadeTOBuilder;
import br.com.crux.builder.UnidadeTOBuilder;
import br.com.crux.dao.repository.ProgramasUnidadeRepository;
import br.com.crux.entity.Programa;
import br.com.crux.entity.ProgramasUnidade;
import br.com.crux.rule.CamposObrigatoriosProgramaUnidadesRule;
import br.com.crux.to.UnidadeTO;

@Component
public class AlterarProgramaUnidadesCmd {

	@Autowired private CamposObrigatoriosProgramaUnidadesRule camposObrigatoriosProgramaUnidadesRule;
	@Autowired private ProgramasUnidadeTOBuilder programasUnidadeTOBuilder;
	@Autowired private ProgramasUnidadeRepository programasUnidadeRepository;
	@Autowired private UnidadeTOBuilder unidadeTOBuilder;

	public void alterarAll(List<UnidadeTO> unidades, Programa programa) {

		// Lista da unidades que o usuário tem acesso.
		List<ProgramasUnidade> listaProgramasUnidade = programasUnidadeRepository.findByPrograma(programa).orElse(new ArrayList<ProgramasUnidade>());

		BiPredicate<ProgramasUnidade, List<UnidadeTO>> contemNaLista = (projetoUnidade, lista) -> lista.stream().anyMatch(unidadeNova -> Objects.nonNull(unidadeNova.getIdUnidade()) && unidadeNova.getIdUnidade().equals(projetoUnidade.getUnidade().getIdUnidade()));

		//Remove do banco todos os itens que não contem na lista
		listaProgramasUnidade.removeIf(unidade -> {
			if (!contemNaLista.test(unidade, unidades)) {
				programasUnidadeRepository.delete(unidade);
				return true;
			}
			return false;
		});

		//Verificar por novas unidades cadastradas
		for (UnidadeTO unidade : unidades) {
			Optional<ProgramasUnidade> findAny = listaProgramasUnidade.stream().filter(pu -> pu.getUnidade().getIdUnidade() == unidade.getIdUnidade()).findAny();

			if (!findAny.isPresent()) {
				alterar(unidade, programa);
			}
		}

	}

	private void alterar(UnidadeTO novo, Programa programa) {
		camposObrigatoriosProgramaUnidadesRule.verificar(novo, programa);
		ProgramasUnidade entity = programasUnidadeTOBuilder.build(programa, unidadeTOBuilder.build(novo));
		programasUnidadeRepository.save(entity);
	}

}
