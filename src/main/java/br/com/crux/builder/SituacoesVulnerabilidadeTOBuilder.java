package br.com.crux.builder;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import br.com.crux.entity.SituacoesVulnerabilidade;
import br.com.crux.to.SituacoesVulnerabilidadeTO;

@Component
public class SituacoesVulnerabilidadeTOBuilder {

	public SituacoesVulnerabilidade build(SituacoesVulnerabilidadeTO p) {
		SituacoesVulnerabilidade retorno = new SituacoesVulnerabilidade();
		BeanUtils.copyProperties(p, retorno);
		return retorno;
	}

	public SituacoesVulnerabilidadeTO buildTO(SituacoesVulnerabilidade p) {
		SituacoesVulnerabilidadeTO retorno = new SituacoesVulnerabilidadeTO();
		BeanUtils.copyProperties(p, retorno);
		return retorno;
	}

	public List<SituacoesVulnerabilidadeTO> buildAll(List<SituacoesVulnerabilidade> dtos) {
		return dtos.stream().map(dto -> buildTO(dto)).collect(Collectors.toList());
	}

}
