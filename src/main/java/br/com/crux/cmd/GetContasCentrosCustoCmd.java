
package br.com.crux.cmd;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.ContasCentrosCustoTOBuilder;
import br.com.crux.dao.repository.ContasCentrosCustoRepository;
import br.com.crux.entity.ContasCentrosCusto;
import br.com.crux.entity.ParceriasPrograma;
import br.com.crux.entity.ParceriasProjeto;
import br.com.crux.to.ContasCentrosCustoTO;

@Component
public class GetContasCentrosCustoCmd {

	@Autowired
	private ContasCentrosCustoRepository contasCentrosCustoRepository;
	@Autowired
	private ContasCentrosCustoTOBuilder contasCentrosCustoTOBuilder;

	public List<ContasCentrosCustoTO> getTOPorParceriasPrograma(ParceriasPrograma p) {
		Optional<List<ContasCentrosCusto>> lista = contasCentrosCustoRepository.findByParceriasPrograma(p);

		if (lista.isPresent()) {
			return contasCentrosCustoTOBuilder.buildAllTO(lista.get());
		}

		return new ArrayList<ContasCentrosCustoTO>();

	}

	public List<ContasCentrosCustoTO> getTOPorParceriasProjeto(ParceriasProjeto p) {
		Optional<List<ContasCentrosCusto>> lista = contasCentrosCustoRepository.findByParceriasProjeto(p);

		if (lista.isPresent()) {
			return contasCentrosCustoTOBuilder.buildAllTO(lista.get());
		}

		return new ArrayList<ContasCentrosCustoTO>();

	}

}
