package br.com.crux.cmd;

import java.util.List;
import java.util.Objects;
import java.util.function.BiPredicate;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.MateriaisProjetoTOBuilder;
import br.com.crux.dao.repository.MateriaisProjetoRepository;
import br.com.crux.entity.MateriaisProjeto;
import br.com.crux.entity.ParceriasProjeto;
import br.com.crux.entity.Projeto;
import br.com.crux.rule.CamposObrigatoriosMateriaisProjetoRule;
import br.com.crux.to.MateriaisProjetoTO;

@Component
public class AlterarMateriaisParceriaProjetoCmd {


	@Autowired private MateriaisProjetoRepository repository;
	@Autowired private CamposObrigatoriosMateriaisProjetoRule camposObrigatoriosRule;
	@Autowired private MateriaisProjetoTOBuilder materiaisProjetoTOBuilder;
	@Autowired private GetMateriaisParceirosProjetoCmd getMateriaisParceirosProjetoCmd;

	private void alterar(Projeto projeto, ParceriasProjeto parceriasProjeto, MateriaisProjetoTO materiaisProjetoTO) {
		camposObrigatoriosRule.verificar(materiaisProjetoTO);
		MateriaisProjeto entity = materiaisProjetoTOBuilder.build(projeto, parceriasProjeto, materiaisProjetoTO);
		repository.save(entity);
	}

	public void alterarAll(Projeto projeto, ParceriasProjeto parceriasProjeto, List<MateriaisProjetoTO> listaTOTela) {
		//Lista de vulnerabilidades do aluno.
		List<MateriaisProjeto> listaDoBanco = getMateriaisParceirosProjetoCmd.getMateriaisProjetoByParceriasProjeto(parceriasProjeto);

		BiPredicate<MateriaisProjetoTO, List<MateriaisProjetoTO>> contemNaLista = (parte, lista) -> lista.stream().anyMatch(registroTO -> Objects.nonNull(registroTO.getId()) && registroTO.getId().equals(parte.getId()));

		//Remove da lista todos os registros que não contém no Banco de Dados
		listaDoBanco.removeIf(registro -> {
			if (!contemNaLista.test(materiaisProjetoTOBuilder.buildTO(registro), listaTOTela)) {
				repository.delete(registro);
				return true;
			}
			return false;
		});

		//Adiciona os novos registros
		List<MateriaisProjetoTO> novos = listaTOTela.stream().filter(registro -> Objects.isNull(registro.getId())).collect(Collectors.toList());

		if (Objects.nonNull(novos)) {
			novos.forEach(novoResponsavel -> alterar(projeto, parceriasProjeto, novoResponsavel));
		}

		//Atualiza os registros 
		listaTOTela.stream().filter(registro -> Objects.nonNull(registro.getId())).forEach(registro -> {
			if (contemNaLista.test(registro, materiaisProjetoTOBuilder.buildAll(listaDoBanco))) {
				alterar(projeto, parceriasProjeto, registro);
			}
		});
	}

}
