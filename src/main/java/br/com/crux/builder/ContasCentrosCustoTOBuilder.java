package br.com.crux.builder;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
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
		
		BeanUtils.copyProperties(contasCentrosCustoTO, entity);

		if (Objects.nonNull(contasCentrosCustoTO.getContasBancaria()) || Objects.nonNull(contasCentrosCustoTO.getContasBancaria().getId())) {
			entity.setContasBancaria(getContasBancariaCmd.getById(contasCentrosCustoTO.getContasBancaria().getId()));
		}

		if(Objects.nonNull(programa) && Objects.nonNull(programa.getId())) { 
			entity.setIdPrograma(programa.getId());
		}

		if(Objects.nonNull(projeto) && Objects.nonNull(projeto.getId())) { 
			entity.setIdProjeto(projeto.getId());
		}

		entity.setUsuarioAlteracao(getUsuarioLogadoCmd.getUsuarioLogado().getIdUsuario());

		return entity;
	}

	public ContasCentrosCustoTO buildTO(ContasCentrosCusto entity) {
		ContasCentrosCustoTO to = new ContasCentrosCustoTO();
		
		if (Objects.isNull(entity)) {
			return to;
		}
		
		BeanUtils.copyProperties(entity, to);

		to.setContasBancaria(contasBancariaTOBuilder.buildTOCombo(entity.getContasBancaria()));

		return to;
	}

	public List<ContasCentrosCustoTO> buildAll(List<ContasCentrosCusto> lista) {
		return lista.stream()
				.map(this::buildTO)
				.collect(Collectors.toList());
	}

	
}
