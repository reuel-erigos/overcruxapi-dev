package br.com.crux.builder;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.entity.VulnerabilidadesFamiliar;
import br.com.crux.to.FamiliaresTO;
import br.com.crux.to.VulnerabilidadesFamiliarTO;

@Component
public class VulnerabilidadesFamiliarTOBuilder {

	@Autowired private FamiliaresTOBuilder familiarBuilder;
	@Autowired private SituacoesVulnerabilidadeTOBuilder situacaoVulnerabilidadeBuilder;
	@Autowired private SolucoesTOBuilder solucaoBuilder;

	public VulnerabilidadesFamiliar build(VulnerabilidadesFamiliarTO p, FamiliaresTO familiarTO) {
		VulnerabilidadesFamiliar retorno = new VulnerabilidadesFamiliar();

		retorno.setId(p.getId());
		retorno.setDataIdentificacao(p.getDataIdentificacao());
		retorno.setDataSolucao(p.getDataSolucao());
		
		retorno.setFamiliar(familiarBuilder.build(familiarTO));
		retorno.setSolucoes(solucaoBuilder.build(p.getSolucoes()));
		retorno.setSituacoesVulnerabilidade(situacaoVulnerabilidadeBuilder.build(p.getSituacoesVulnerabilidade()));
		
		retorno.setUsuarioAlteracao(p.getUsuarioAlteracao());

		return retorno;
	}

	public VulnerabilidadesFamiliarTO buildTO(VulnerabilidadesFamiliar p) {
		VulnerabilidadesFamiliarTO retorno = new VulnerabilidadesFamiliarTO();
		
		retorno.setId(p.getId());
		retorno.setDataIdentificacao(p.getDataIdentificacao());
		retorno.setDataSolucao(p.getDataSolucao());
		
		retorno.setFamiliar(familiarBuilder.buildSemRelacionamentoTO(p.getFamiliar()));
		retorno.setSolucoes(solucaoBuilder.buildTO(p.getSolucoes()));
		retorno.setSituacoesVulnerabilidade(situacaoVulnerabilidadeBuilder.buildTO(p.getSituacoesVulnerabilidade()));
		
		retorno.setUsuarioAlteracao(p.getUsuarioAlteracao());

		return retorno;
	}

	public List<VulnerabilidadesFamiliarTO> buildAll(List<VulnerabilidadesFamiliar> dtos) {
		return dtos.stream().map(dto -> buildTO(dto)).collect(Collectors.toList());
	}

}
