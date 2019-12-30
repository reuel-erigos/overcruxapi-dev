package br.com.crux.builder;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.entity.EntidadesSociais;
import br.com.crux.to.EntidadesSociaisTO;

@Component
public class EntidadesSociaisTOBuilder {

	@Autowired
	private EmpresaTOBuilder empresaBuilder;

	public EntidadesSociais build(EntidadesSociaisTO p) {
		EntidadesSociais retorno = new EntidadesSociais();

		retorno.setId(p.getId());
		retorno.setDataFim(p.getDataFim());
		retorno.setDataVinculo(p.getDataVinculo());
		retorno.setEmpresa(empresaBuilder.build(p.getEmpresa()));
		retorno.setUsuarioAlteracao(p.getUsuarioAlteracao());

		return retorno;
	}

	public EntidadesSociaisTO buildTO(EntidadesSociais p) {
		EntidadesSociaisTO retorno = new EntidadesSociaisTO();
		
		retorno.setId(p.getId());
		retorno.setDataFim(p.getDataFim());
		retorno.setDataVinculo(p.getDataVinculo());
		retorno.setEmpresa(empresaBuilder.buildTO(p.getEmpresa()));
		retorno.setUsuarioAlteracao(p.getUsuarioAlteracao());

		return retorno;
	}

	public List<EntidadesSociaisTO> buildAll(List<EntidadesSociais> dtos) {
		return dtos.stream().map(dto -> buildTO(dto)).collect(Collectors.toList());
	}

}
