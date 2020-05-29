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
import br.com.crux.entity.ParceriasPrograma;
import br.com.crux.to.ParceriasCategoriasTO;

@Component
public class AlterarParceriasCategoriasProgramaCmd {

	@Autowired private ParceriasCategoriasTOBuilder toBuilder;
	@Autowired private ParceriasCategoriasRepository repository;
	@Autowired private GetParceriasCategoriasCmd getParceriasCategoriasCmd;
	

	private void alterar(ParceriasPrograma parceriasPrograma, ParceriasCategoriasTO parceriasCategoriasTO) {
		ParceriasCategorias entity = toBuilder.buildEntity(parceriasPrograma , null, parceriasCategoriasTO);
		repository.save(entity);
	}
	

	public void alterarAll(ParceriasPrograma parceriasPrograma, List<ParceriasCategoriasTO> listaTOTela) {
		//Lista do banco
		List<ParceriasCategorias> listaDoBanco = getParceriasCategoriasCmd.getParceriasCategoriasByParceriasPrograma(parceriasPrograma);

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
			novos.forEach(novoResponsavel -> alterar(parceriasPrograma, novoResponsavel));
		}

		//Atualiza os registros 
		listaTOTela.stream().filter(registro -> Objects.nonNull(registro.getId())).forEach(registro -> {
			if (contemNaLista.test(registro, toBuilder.buildAllTO(listaDoBanco))) {
				alterar(parceriasPrograma, registro);
			}
		});
	}


}