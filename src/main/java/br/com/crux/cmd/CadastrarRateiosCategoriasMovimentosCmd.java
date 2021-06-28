package br.com.crux.cmd;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.RateiosCategoriasMovimentosTOBuilder;
import br.com.crux.dao.repository.RateiosCategoriasMovimentosRepository;
import br.com.crux.entity.CategoriasMovimentos;
import br.com.crux.entity.RateiosCategoriasMovimentos;
import br.com.crux.to.RateiosCategoriasMovimentosTO;

@Component
public class CadastrarRateiosCategoriasMovimentosCmd {

	@Autowired private RateiosCategoriasMovimentosRepository repository;
	@Autowired private RateiosCategoriasMovimentosTOBuilder tOBuilder;

	public RateiosCategoriasMovimentos cadastrar(RateiosCategoriasMovimentosTO to, CategoriasMovimentos pai) {
		to.setIdCategoriaMovimento(pai.getId());
		RateiosCategoriasMovimentos entity = tOBuilder.build(to);
		return repository.save(entity);
	}

	public List<RateiosCategoriasMovimentos> cadastrarLista(CategoriasMovimentos pai, List<RateiosCategoriasMovimentosTO> lista) {
		return lista.stream()
				.map(item -> cadastrar(item, pai))
				.collect(Collectors.toList());

	}

}
