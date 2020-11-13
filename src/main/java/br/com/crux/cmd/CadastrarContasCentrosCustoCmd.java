package br.com.crux.cmd;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.ContasCentrosCustoTOBuilder;
import br.com.crux.dao.repository.ContasCentrosCustoRepository;
import br.com.crux.entity.ContasCentrosCusto;
import br.com.crux.entity.ParceriasPrograma;
import br.com.crux.entity.ParceriasProjeto;
import br.com.crux.to.ContasCentrosCustoTO;

@Component
public class CadastrarContasCentrosCustoCmd {

	@Autowired
	ContasCentrosCustoRepository repository;
	@Autowired
	ContasCentrosCustoTOBuilder contasCentrosCustoTOBuilder;

	public ContasCentrosCusto cadastrar(ParceriasPrograma parceriasPrograma, ParceriasProjeto parceriasProjeto,
			ContasCentrosCustoTO contasCentrosCustoTO) {
		ContasCentrosCusto contasCentrosCusto = contasCentrosCustoTOBuilder.build(contasCentrosCustoTO,parceriasPrograma,parceriasProjeto);
		return repository.save(contasCentrosCusto);
	}

	public List<ContasCentrosCusto> cadastrarLista(ParceriasPrograma parceriasPrograma, ParceriasProjeto parceriasProjeto,
			List<ContasCentrosCustoTO> list) {
		return list.stream().map(contasCentrosCusto -> cadastrar(parceriasPrograma, parceriasProjeto, contasCentrosCusto))
				.collect(Collectors.toList());

	}

}
