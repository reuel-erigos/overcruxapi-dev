package br.com.crux.cmd;

import java.util.List;
import java.util.Objects;
import java.util.function.BiPredicate;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.ParceriasCategoriasTOBuilder;
import br.com.crux.dao.repository.ParceriasCategoriasRepository;
import br.com.crux.entity.ParceriasCategorias;
import br.com.crux.entity.ParceriasProjeto;
import br.com.crux.entity.Projeto;
import br.com.crux.to.ParceriasCategoriasTO;

@Component
public class AlterarParceriasCategoriasProjetoCmd  {

	@Autowired private ParceriasCategoriasTOBuilder toBuilder;
	@Autowired private ParceriasCategoriasRepository repository;
	@Autowired private GetParceriasCategoriasCmd getParceriasCategoriasCmd;
	
	
	private void alterar(Projeto projeto, ParceriasProjeto parceriasProjeto, ParceriasCategoriasTO parceriasCategoriasTO) {
		ParceriasCategorias entity = toBuilder.buildEntity(null , parceriasProjeto, parceriasCategoriasTO);
		repository.save(entity);
	}
	

	public void alterarAll(Projeto projeto, ParceriasProjeto parceriasProjeto, List<ParceriasCategoriasTO> listaTOTela) {
		//Lista do banco
		List<ParceriasCategorias> listaDoBanco = getParceriasCategoriasCmd.getParceriasCategoriasByParceriasProjeto(parceriasProjeto);

		BiPredicate<ParceriasCategoriasTO, List<ParceriasCategoriasTO>> contemNaLista = (parte, lista) -> lista.stream().anyMatch(registroTO -> Objects.nonNull(registroTO.getId()) && registroTO.getId().equals(parte.getId()));

		//Remove da lista todos os registros que não contém no Banco de Dados
		listaDoBanco.removeIf(registro -> {
			if (!contemNaLista.test(toBuilder.buildTO(registro), listaTOTela)) {
				repository.delete(registro);
				return true;
			}
			return false;
		});

		//Adiciona os novos registros
		List<ParceriasCategoriasTO> novos = listaTOTela.stream().filter(registro -> Objects.isNull(registro.getId())).collect(Collectors.toList());

		if (Objects.nonNull(novos)) {
			novos.forEach(novoResponsavel -> alterar(projeto, parceriasProjeto, novoResponsavel));
		}

		//Atualiza os registros 
		listaTOTela.stream().filter(registro -> Objects.nonNull(registro.getId())).forEach(registro -> {
			if (contemNaLista.test(registro, toBuilder.buildAllTO(listaDoBanco))) {
				alterar(projeto, parceriasProjeto, registro);
			}
		});
	}

}