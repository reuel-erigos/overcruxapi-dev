package br.com.crux.cmd;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.ContasCentrosCustoTOBuilder;
import br.com.crux.dao.repository.ContasCentrosCustoRepository;
import br.com.crux.entity.ContasCentrosCusto;
import br.com.crux.entity.Programa;
import br.com.crux.entity.Projeto;
import br.com.crux.to.ContasCentrosCustoTO;

@Component
public class GetContasCentrosCustoCmd {

	@Autowired private ContasCentrosCustoRepository contasCentrosCustoRepository;
	@Autowired private ContasCentrosCustoTOBuilder contasCentrosCustoTOBuilder;

	public List<ContasCentrosCustoTO> getTOPorPrograma(Programa param) {
		Optional<List<ContasCentrosCusto>> lista = contasCentrosCustoRepository.findByPrograma(param);

		if (lista.isPresent()) {
			return contasCentrosCustoTOBuilder.buildAll(lista.get());
		}

		return new ArrayList<ContasCentrosCustoTO>();

	}

	public List<ContasCentrosCustoTO> getTOPorProjeto(Projeto p) {
		Optional<List<ContasCentrosCusto>> lista = contasCentrosCustoRepository.findByProjeto(p);

		if (lista.isPresent()) {
			return contasCentrosCustoTOBuilder.buildAll(lista.get());
		}

		return new ArrayList<ContasCentrosCustoTO>();

	}

}
