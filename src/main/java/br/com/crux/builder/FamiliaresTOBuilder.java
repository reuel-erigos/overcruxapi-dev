package br.com.crux.builder;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.cmd.GetResponsaveisAlunoCmd;
import br.com.crux.cmd.GetVulnerabilidadesFamiliarCmd;
import br.com.crux.entity.Familiares;
import br.com.crux.enums.SituacaoParentesco;
import br.com.crux.to.FamiliarResponsavelTO;
import br.com.crux.to.FamiliaresTO;

@Component
public class FamiliaresTOBuilder {

	@Autowired private AlunoTOBuilder alunoBuilder;
	@Autowired private PessoaFisicaTOBuilder pessoaFisicaBuilder;
	@Autowired private GetResponsaveisAlunoCmd getResponsaveisAlunoCmd;
	@Autowired private GetVulnerabilidadesFamiliarCmd getVulnerabilidadesFamiliarCmd;
	
	
	public Familiares build(FamiliaresTO p) {
		Familiares retorno = new Familiares();

		retorno.setId(p.getId());
		retorno.setDescGrauParentesco(p.getDescGrauParentesco());
		
		if( StringUtils.isNoneEmpty(p.getSituacaoParentesco())) {
			SituacaoParentesco porTipo = SituacaoParentesco.getPorTipo(p.getSituacaoParentesco());
			retorno.setSituacaoParentesco(porTipo);
		}
		
		retorno.setDescOutrasInformacoes(p.getDescOutrasInformacoes());
		retorno.setDescDesligamento(p.getDescDesligamento());
		retorno.setPessoasFisica(pessoaFisicaBuilder.build(p.getPessoasFisica()));
		retorno.setAluno(alunoBuilder.build(p.getAluno()));
		retorno.setUsuarioAlteracao(p.getUsuarioAlteracao());
		retorno.setDataCadastro(p.getDataCadastro());
		retorno.setDataDesligamento(p.getDataDesligamento());
		
		return retorno;
	}

	public FamiliaresTO buildTO(Familiares p) {
		FamiliaresTO retorno = new FamiliaresTO();
		
		retorno = buildSemRelacionamentoTO(p);

		if(Objects.nonNull(p.getId())) {
			retorno.setResponsaveis(getResponsaveisAlunoCmd.getAllByFamiliar(p.getId()));
			retorno.setVulnerabilidades(getVulnerabilidadesFamiliarCmd.getAllFamiliarTO(p.getId()));
		}
		
		return retorno;
	}
	
	
	public FamiliaresTO buildSemRelacionamentoTO(Familiares p) {
		FamiliaresTO retorno = new FamiliaresTO();
		
		if(Objects.isNull(p)) {
			return retorno;
		}
		
		retorno.setId(p.getId());
		retorno.setDescGrauParentesco(p.getDescGrauParentesco());
		
		if(Objects.nonNull(p.getSituacaoParentesco())) {
			SituacaoParentesco porTipo = SituacaoParentesco.getPorTipo(p.getSituacaoParentesco().getTipo());
			retorno.setSituacaoParentesco(porTipo.getTipo());
		}
		
		retorno.setDescOutrasInformacoes(p.getDescOutrasInformacoes());
		retorno.setDescDesligamento(p.getDescDesligamento());
		retorno.setPessoasFisica(pessoaFisicaBuilder.buildTO(p.getPessoasFisica()));
		retorno.setAluno(alunoBuilder.buildTO(p.getAluno()));
		retorno.setUsuarioAlteracao(p.getUsuarioAlteracao());
		retorno.setDataCadastro(p.getDataCadastro());
		retorno.setDataDesligamento(p.getDataDesligamento());

		
		return retorno;
	}
	
	public FamiliarResponsavelTO buildResponsavalTO(Familiares p) {
		FamiliarResponsavelTO retorno = new FamiliarResponsavelTO();
		
		retorno.setId(p.getId());
		retorno.setDescGrauParentesco(p.getDescGrauParentesco());
		
		if(Objects.nonNull(p.getSituacaoParentesco())) {
			SituacaoParentesco porTipo = SituacaoParentesco.getPorTipo(p.getSituacaoParentesco().getTipo());
			retorno.setSituacaoParentesco(porTipo.getTipo());
		}
		
		retorno.setDescOutrasInformacoes(p.getDescOutrasInformacoes());
		retorno.setDescDesligamento(p.getDescDesligamento());
		retorno.setPessoasFisica(pessoaFisicaBuilder.buildTO(p.getPessoasFisica()));
		retorno.setAluno(alunoBuilder.buildTO(p.getAluno()));
		retorno.setUsuarioAlteracao(p.getUsuarioAlteracao());
		retorno.setDataCadastro(p.getDataCadastro());
		retorno.setDataDesligamento(p.getDataDesligamento());

		return retorno;
	}
	
	
	public List<FamiliaresTO> buildAll(List<Familiares> dtos) {
		return dtos.stream().map(dto -> buildTO(dto)).collect(Collectors.toList());
	}

}
