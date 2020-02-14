package br.com.crux.cmd;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.ContasCentrosCustoTOBuilder;
import br.com.crux.dao.repository.ContasCentrosCustoRepository;
import br.com.crux.entity.ContasCentrosCusto;
import br.com.crux.entity.Programa;
import br.com.crux.entity.Projeto;
import br.com.crux.to.ContasCentrosCustoTO;

@Component
public class CadastrarContasCentrosCustoCmd {

	@Autowired ContasCentrosCustoRepository repository;
	@Autowired ContasCentrosCustoTOBuilder contasCentrosCustoTOBuilder;

	public ContasCentrosCusto cadastrar(Programa programa, Projeto projeto, ContasCentrosCustoTO contasCentrosCustoTO) {
		ContasCentrosCusto contasCentrosCusto = contasCentrosCustoTOBuilder.build(programa, projeto, contasCentrosCustoTO);
		return repository.save(contasCentrosCusto);
	}

	public List<ContasCentrosCusto> cadastrarLista(Programa programa, Projeto projeto, List<ContasCentrosCustoTO> list) {
		return list.stream()
				.map(contasCentrosCusto -> cadastrar(programa, projeto,contasCentrosCusto))
				.collect(Collectors.toList());

	}

}
