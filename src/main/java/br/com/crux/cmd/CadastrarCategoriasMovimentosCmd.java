package br.com.crux.cmd;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.CategoriasMovimentosTOBuilder;
import br.com.crux.dao.base.BaseDao;
import br.com.crux.dao.repository.CategoriasMovimentosRepository;
import br.com.crux.entity.CategoriasMovimentos;
import br.com.crux.entity.Movimentacoes;
import br.com.crux.to.CategoriasMovimentosTO;

@Component
public class CadastrarCategoriasMovimentosCmd extends BaseDao {

	@Autowired private CategoriasMovimentosRepository repository;
	@Autowired private CategoriasMovimentosTOBuilder tOBuilder;

	public CategoriasMovimentos cadastrar(CategoriasMovimentosTO to, Movimentacoes movimentacoes) {
		CategoriasMovimentos entity = tOBuilder.build(movimentacoes, to);
		CategoriasMovimentos entitySalva = repository.save(entity);
		return entitySalva;
	}

	public List<CategoriasMovimentos> cadastrarLista(Movimentacoes movimentacoes, List<CategoriasMovimentosTO> lista) {
		return lista.stream()
				.map(item -> cadastrar(item, movimentacoes))
				.collect(Collectors.toList());

	}

}
