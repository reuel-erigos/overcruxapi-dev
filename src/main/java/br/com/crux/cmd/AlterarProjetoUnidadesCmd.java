package br.com.crux.cmd;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.BiPredicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.ProjetosUnidadeTOBuilder;
import br.com.crux.builder.UnidadeTOBuilder;
import br.com.crux.dao.repository.ProjetosUnidadeRepository;
import br.com.crux.entity.Projeto;
import br.com.crux.entity.ProjetosUnidade;
import br.com.crux.rule.CamposObrigatoriosProjetoUnidadesRule;
import br.com.crux.to.UnidadeTO;

@Component
public class AlterarProjetoUnidadesCmd {

	@Autowired private CamposObrigatoriosProjetoUnidadesRule camposObrigatoriosProjetoUnidadesRule;
	@Autowired private ProjetosUnidadeTOBuilder projetosUnidadeTOBuilder;
	@Autowired private ProjetosUnidadeRepository projetosUnidadeRepository;
	@Autowired private UnidadeTOBuilder unidadeTOBuilder;

	public void alterarAll(List<UnidadeTO> unidades, Projeto projeto) {

		// Lista da unidades que o usuário tem acesso.
		List<ProjetosUnidade> listaProjetoUnidades = projetosUnidadeRepository.findByProjeto(projeto).orElse(new ArrayList<ProjetosUnidade>());

		BiPredicate<ProjetosUnidade, List<UnidadeTO>> contemNaLista = (projetoUnidade, lista) -> 
		    lista.stream().anyMatch(unidadeNova -> Objects.nonNull(unidadeNova.getIdUnidade()) && unidadeNova.getIdUnidade().equals(projetoUnidade.getUnidade().getIdUnidade()));

		//Remove do banco todos os itens que não contem na lista
		listaProjetoUnidades.removeIf(unidade -> {
			if (!contemNaLista.test(unidade, unidades)) {
				projetosUnidadeRepository.delete(unidade);
				return true;
			}
			return false;
		});


		//Verificar por novas unidades cadastradas
		for (UnidadeTO unidade : unidades) {
			Optional<ProjetosUnidade> findAny = listaProjetoUnidades.stream().filter(pu -> pu.getUnidade().getIdUnidade() == unidade.getIdUnidade()).findAny();
			
			if(!findAny.isPresent()) {
				alterar(unidade, projeto);
			}
		}

	}

	private void alterar(UnidadeTO novo, Projeto projeto) {
		camposObrigatoriosProjetoUnidadesRule.verificar(novo, projeto);
		ProjetosUnidade entity = projetosUnidadeTOBuilder.build(projeto, unidadeTOBuilder.build(novo));
		projetosUnidadeRepository.save(entity);
	}

}
