package br.com.crux.cmd;

import java.util.List;
import java.util.Objects;
import java.util.function.BiPredicate;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.MateriaisProgramaTOBuilder;
import br.com.crux.dao.repository.MateriaisProgramaRepository;
import br.com.crux.entity.MateriaisPrograma;
import br.com.crux.entity.ParceriasPrograma;
import br.com.crux.entity.Programa;
import br.com.crux.rule.CamposObrigatoriosMateriaisProgramaRule;
import br.com.crux.to.MateriaisProgramaTO;

@Component
public class AlterarMateriaisParceriaProgramaCmd {


	@Autowired private MateriaisProgramaRepository repository;
	@Autowired private CamposObrigatoriosMateriaisProgramaRule camposObrigatoriosRule;
	@Autowired private MateriaisProgramaTOBuilder materiaisProgramaTOBuilder;
	@Autowired private GetMateriaisParceirosProgramaCmd getMateriaisParceirosProgramaCmd;

	private void alterar(Programa programa, ParceriasPrograma parceriasPrograma, MateriaisProgramaTO materiaisProgramaTO) {
		camposObrigatoriosRule.verificar(materiaisProgramaTO);
		MateriaisPrograma entity = materiaisProgramaTOBuilder.build(programa, parceriasPrograma, materiaisProgramaTO);
		repository.save(entity);
	}

	public void alterarAll(Programa programa, ParceriasPrograma parceriasPrograma, List<MateriaisProgramaTO> listaTOTela) {
		//Lista de vulnerabilidades do aluno.
		List<MateriaisPrograma> listaDoBanco = getMateriaisParceirosProgramaCmd.getMateriaisProgramaByParceriasPrograma(parceriasPrograma);

		BiPredicate<MateriaisProgramaTO, List<MateriaisProgramaTO>> contemNaLista = (parte, lista) -> lista.stream().anyMatch(registroTO -> Objects.nonNull(registroTO.getId()) && registroTO.getId().equals(parte.getId()));

		//Remove da lista todos os registros que não contém no Banco de Dados
		listaDoBanco.removeIf(registro -> {
			if (!contemNaLista.test(materiaisProgramaTOBuilder.buildTO(registro), listaTOTela)) {
				repository.delete(registro);
				return true;
			}
			return false;
		});

		//Adiciona os novos registros
		List<MateriaisProgramaTO> novos = listaTOTela.stream().filter(registro -> Objects.isNull(registro.getId())).collect(Collectors.toList());

		if (Objects.nonNull(novos)) {
			novos.forEach(novoResponsavel -> alterar(programa, parceriasPrograma, novoResponsavel));
		}

		//Atualiza os registros 
		listaTOTela.stream().filter(registro -> Objects.nonNull(registro.getId())).forEach(registro -> {
			if (contemNaLista.test(registro, materiaisProgramaTOBuilder.buildAll(listaDoBanco))) {
				alterar(programa, parceriasPrograma, registro);
			}
		});
	}

}
