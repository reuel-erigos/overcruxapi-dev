package br.com.crux.builder;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.cmd.GetBeneficioSocialPessoaFisicaCmd;
import br.com.crux.cmd.GetResponsaveisAlunoCmd;
import br.com.crux.cmd.GetVulnerabilidadesFamiliarCmd;
import br.com.crux.dao.dto.ComboFamiliarDTO;
import br.com.crux.entity.Familiares;
import br.com.crux.enums.SituacaoParentesco;
import br.com.crux.to.BeneficioSocialPessoaFisicaTO;
import br.com.crux.to.ComboFamiliarTO;
import br.com.crux.to.FamiliarResponsavelTO;
import br.com.crux.to.FamiliaresTO;
import br.com.crux.to.PessoaFisicaTO;

@Component
public class FamiliaresTOBuilder {

	@Autowired private AlunoTOBuilder alunoBuilder;
	@Autowired private PessoaFisicaTOBuilder pessoaFisicaBuilder;
	@Autowired private GrausParentescoTOBuilder grausParentescoTOBuilder;
	@Autowired private GetResponsaveisAlunoCmd getResponsaveisAlunoCmd;
	@Autowired private GetVulnerabilidadesFamiliarCmd getVulnerabilidadesFamiliarCmd;
	@Autowired private GetBeneficioSocialPessoaFisicaCmd getBeneficioSocialPessoaFisicaCmd;
	
	
	
	public Familiares build(FamiliaresTO p) {
		Familiares retorno = new Familiares();

		retorno.setId(p.getId());
		
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
		if(Objects.nonNull(p.getGrauParentesco()) && Objects.nonNull(p.getGrauParentesco().getId())) {
			retorno.setGrauParentesco(grausParentescoTOBuilder.build(p.getGrauParentesco()));
		}
		
		return retorno;
	}

	public FamiliaresTO buildTO(Familiares p) {
		FamiliaresTO retorno = new FamiliaresTO();
		
		retorno = buildSemRelacionamentoTO(p);

		if(Objects.nonNull(p.getId())) {
			retorno.setResponsaveis(getResponsaveisAlunoCmd.getAllByFamiliar(p.getId()));
			retorno.setVulnerabilidades(getVulnerabilidadesFamiliarCmd.getAllFamiliarTO(p.getId()));
		}
		if(Objects.nonNull(p.getId())) {
			retorno.getPessoasFisica().setBeneficiosSociaisPessoaFisica(getBeneficioSocialPessoaFisicaCmd.getAllPorPessoaFisicaTO(p.getPessoasFisica().getId()));
		}
		
		if(Objects.nonNull(p.getGrauParentesco()) && Objects.nonNull(p.getGrauParentesco().getId())) {
			retorno.setGrauParentesco(grausParentescoTOBuilder.buildTO(p.getGrauParentesco()));
		}
		
		retorno.getPessoasFisica().setValorRenda(calcularValorTotal(retorno.getPessoasFisica()));
		return retorno;
	}
	
	
	public ComboFamiliarTO buildComboTO(ComboFamiliarDTO dto) {
		ComboFamiliarTO retorno = new ComboFamiliarTO();
		
		BeanUtils.copyProperties(dto, retorno);
		
		return retorno;
	}
	
	
	public FamiliaresTO buildSemRelacionamentoTO(Familiares p) {
		FamiliaresTO retorno = new FamiliaresTO();
		
		if(Objects.isNull(p)) {
			return retorno;
		}
		
		retorno.setId(p.getId());
		
		if(Objects.nonNull(p.getSituacaoParentesco())) {
			SituacaoParentesco porTipo = SituacaoParentesco.getPorTipo(p.getSituacaoParentesco().getTipo());
			retorno.setSituacaoParentesco(porTipo.getTipo());
		}
		
		if(!Objects.isNull(p.getGrauParentesco()) && !Objects.isNull(p.getGrauParentesco().getId())) {
			retorno.setGrauParentesco(grausParentescoTOBuilder.buildTO(p.getGrauParentesco()));
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
		if(Objects.nonNull(retorno.getPessoasFisica().getId())) {
			retorno.getPessoasFisica().setBeneficiosSociaisPessoaFisica(getBeneficioSocialPessoaFisicaCmd.getAllPorPessoaFisicaTO(p.getPessoasFisica().getId()));
		}
		if(!Objects.isNull(p.getGrauParentesco()) && !Objects.isNull(p.getGrauParentesco().getId())) {
			retorno.setGrauParentesco(grausParentescoTOBuilder.buildTO(p.getGrauParentesco()));
		}
		retorno.getPessoasFisica().setValorRenda(calcularValorTotal(retorno.getPessoasFisica()));

		return retorno;
	}
	
	
	public List<FamiliaresTO> buildAll(List<Familiares> dtos) {
		return dtos.stream().map(dto -> buildTO(dto)).collect(Collectors.toList());
	}

	
	
	public List<ComboFamiliarTO> buildAllDTO(List<ComboFamiliarDTO> dtos) {
		return dtos.stream().map(dto -> buildComboTO(dto)).collect(Collectors.toList());
	}
	
	private Double calcularValorTotal(PessoaFisicaTO p) {
		Double rendaAposentadoria = p.getValorRendaAposentadoria() != null ? p.getValorRendaAposentadoria() : 0;
		Double rendaAutonomo = p.getValorRendaAutonomo() != null ? p.getValorRendaAutonomo() : 0;
		Double rendaCtps = p.getValorRendaCtps() != null ? p.getValorRendaCtps() : 0;
		Double rendaPensaoAlimenticia = p.getValorRendaPensaoAlimenticia() != null ? p.getValorRendaPensaoAlimenticia() : 0;
		Double valorTotal = rendaAposentadoria + rendaAutonomo + rendaCtps + rendaPensaoAlimenticia;
		if(p.getBeneficiosSociaisPessoaFisica() != null) {
			for (BeneficioSocialPessoaFisicaTO beneficio : p.getBeneficiosSociaisPessoaFisica()) {
				if(beneficio.getDataInicio().isBefore(LocalDateTime.now()) && (beneficio.getDataFim() == null || beneficio.getDataInicio().isAfter(LocalDateTime.now()))) {
					valorTotal = valorTotal + beneficio.getValor();
				}
			}
		}
		return valorTotal;
	}
}
