package br.com.crux.builder;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.entity.AnexosAcaoPlanejamento;
import br.com.crux.to.AnexosAcaoPlanejamentoTO;

@Component
public class AnexosAcaoPlanejamentoTOBuilder {
	
	@Autowired private ArquivoMetadadosTOBuilder arquivoMetadadosTOBuilder;

	public AnexosAcaoPlanejamentoTO buildTO(AnexosAcaoPlanejamento p) {
		AnexosAcaoPlanejamentoTO retorno = new AnexosAcaoPlanejamentoTO();
		
		if(Objects.isNull(p)) {
			return retorno;
		}
		
		BeanUtils.copyProperties(p, retorno);
		retorno.setMetadados(arquivoMetadadosTOBuilder.buildTO(p.getMetadados()));
		
		return retorno;
	}

	public AnexosAcaoPlanejamento build(AnexosAcaoPlanejamentoTO to) {
		AnexosAcaoPlanejamento retorno = new AnexosAcaoPlanejamento();
		
		BeanUtils.copyProperties(to, retorno);
		retorno.setMetadados(arquivoMetadadosTOBuilder.build(to.getMetadados()));

		return retorno;
	}


	public List<AnexosAcaoPlanejamentoTO> buildAllTO(List<AnexosAcaoPlanejamento> lista) {
		return lista.stream().map(this::buildTO).collect(Collectors.toList());
	}
	
	public List<AnexosAcaoPlanejamento> buildAll(List<AnexosAcaoPlanejamentoTO> dtos) {
		return dtos.stream().map(dto -> build(dto)).collect(Collectors.toList());
	}

	
}
