package br.com.crux.builder;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.cmd.GetContasBancariaCmd;
import br.com.crux.cmd.GetUsuarioLogadoCmd;
import br.com.crux.entity.ContasCentrosCusto;
import br.com.crux.entity.Programa;
import br.com.crux.entity.Projeto;
import br.com.crux.to.ContasCentrosCustoTO;

@Component
public class ContasCentrosCustoTOBuilder {

	@Autowired GetUsuarioLogadoCmd getUsuarioLogadoCmd;
	@Autowired GetContasBancariaCmd getContasBancariaCmd;
	@Autowired ContasBancariaTOBuilder contasBancariaTOBuilder;

	public ContasCentrosCusto build(Programa programa, Projeto projeto, ContasCentrosCustoTO contasCentrosCustoTO) {
		ContasCentrosCusto entity = new ContasCentrosCusto();
		
		entity.setId(contasCentrosCustoTO.getId());

		if (Objects.nonNull(contasCentrosCustoTO.getContasBancaria()) || Objects.nonNull(contasCentrosCustoTO.getContasBancaria()
				.getId())) {
			entity.setContasBancaria(getContasBancariaCmd.getById(contasCentrosCustoTO.getContasBancaria()
					.getId()));
		}

		if (Objects.nonNull(programa)) {
			entity.setPrograma(programa);
		}

		if (Objects.nonNull(projeto)) {
			entity.setProjeto(projeto);
		}

		entity.setUsuarioAlteracao(getUsuarioLogadoCmd.getUsuarioLogado()
				.getIdUsuario());

		return entity;
	}

	public ContasCentrosCustoTO buildTO(ContasCentrosCusto entity) {
		ContasCentrosCustoTO to = new ContasCentrosCustoTO();

		to.setId(entity.getId());
		to.setContasBancaria(contasBancariaTOBuilder.buildTOEnxuto(entity.getContasBancaria()));

		return to;
	}

	public List<ContasCentrosCustoTO> buildAll(List<ContasCentrosCusto> lista) {
		return lista.stream()
				.map(this::buildTO)
				.collect(Collectors.toList());
	}

	
}
