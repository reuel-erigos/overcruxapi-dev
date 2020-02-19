package br.com.crux.builder;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.entity.Estoques;
import br.com.crux.to.EstoquesTO;

@Component
public class EstoquesTOBuilder {

	@Autowired private MaterialTOBuilder materialTOBuilder;
	@Autowired private UnidadeTOBuilder unidadeTOBuilder;
	@Autowired private FuncionarioTOBuilder funcionarioTOBuilder;
	@Autowired private DepartamentoTOBuilder departamentoTOBuilder;
	@Autowired private ProgramaTOBuilder programaTOBuilder;
	@Autowired private ProjetoTOBuilder projetoTOBuilder;

	
	public Estoques build(EstoquesTO p) {
		Estoques retorno = new Estoques();

		retorno.setId(p.getId());
		
		retorno.setDataEstoque(p.getDataEstoque());
		retorno.setDtAtzEstoque(p.getDtAtzEstoque());
		retorno.setQuantidadeEstoque(p.getQuantidadeEstoque());
		retorno.setValorMedioMaterial(p.getValorMedioMaterial());
		retorno.setValorUltimoMaterial(p.getValorUltimoMaterial());
		retorno.setQuantidadeMinimoEstoque(p.getQuantidadeMinimoEstoque());
		retorno.setQuantidadeMaximoEstoque(p.getQuantidadeMaximoEstoque());
		retorno.setValorEntradaMaterial(p.getValorEntradaMaterial());
		retorno.setDescricaoMovimentacaoEstoque(p.getDescricaoMovimentacaoEstoque());
		retorno.setDescricaoFormaValorEntrada(p.getDescricaoFormaValorEntrada());
		retorno.setTipoEntradaMaterial(p.getTipoEntradaMaterial());
		retorno.setTipoMovimentacaoEstoque(p.getTipoMovimentacaoEstoque());
		
		
		if(Objects.nonNull(p.getMaterial()) && Objects.nonNull(p.getMaterial().getId())) {
			retorno.setMaterial(materialTOBuilder.build(p.getMaterial()));
		}
		if(Objects.nonNull(p.getUnidade()) && Objects.nonNull(p.getUnidade().getIdUnidade())) {
			retorno.setUnidade(unidadeTOBuilder.build(p.getUnidade()));
		}
		if(Objects.nonNull(p.getFuncionario()) && Objects.nonNull(p.getFuncionario().getId())) {
			retorno.setFuncionario(funcionarioTOBuilder.build(p.getFuncionario()));
		}
		if(Objects.nonNull(p.getDepartamento()) && Objects.nonNull(p.getDepartamento().getIdDepartamento())) {
			retorno.setDepartamento(departamentoTOBuilder.build(p.getDepartamento()));
		}
		if(Objects.nonNull(p.getPrograma()) && Objects.nonNull(p.getPrograma().getId())) {
			retorno.setPrograma(programaTOBuilder.build(p.getPrograma()));
		}
		if(Objects.nonNull(p.getProjeto()) && Objects.nonNull(p.getProjeto().getId())) {
			retorno.setProjeto(projetoTOBuilder.build(p.getProjeto()));
		}
		
		
		retorno.setUsuarioAlteracao(p.getUsuarioAlteracao());
		
		return retorno;
	}

	public EstoquesTO buildTO(Estoques p) {
		EstoquesTO retorno = new EstoquesTO();
		
		if(Objects.isNull(p)) {
			return retorno;
		}
		
		retorno.setId(p.getId());
		
		retorno.setDataEstoque(p.getDataEstoque());
		retorno.setDtAtzEstoque(p.getDtAtzEstoque());
		retorno.setQuantidadeEstoque(p.getQuantidadeEstoque());
		retorno.setValorMedioMaterial(p.getValorMedioMaterial());
		retorno.setValorUltimoMaterial(p.getValorUltimoMaterial());
		retorno.setQuantidadeMinimoEstoque(p.getQuantidadeMinimoEstoque());
		retorno.setQuantidadeMaximoEstoque(p.getQuantidadeMaximoEstoque());
		retorno.setValorEntradaMaterial(p.getValorEntradaMaterial());
		retorno.setDescricaoMovimentacaoEstoque(p.getDescricaoMovimentacaoEstoque());
		retorno.setDescricaoFormaValorEntrada(p.getDescricaoFormaValorEntrada());
		retorno.setTipoEntradaMaterial(p.getTipoEntradaMaterial());
		retorno.setTipoMovimentacaoEstoque(p.getTipoMovimentacaoEstoque());
		
		
		if(Objects.nonNull(p.getMaterial()) && Objects.nonNull(p.getMaterial().getId())) {
			retorno.setMaterial(materialTOBuilder.buildTO(p.getMaterial()));
		}
		if(Objects.nonNull(p.getUnidade()) && Objects.nonNull(p.getUnidade().getIdUnidade())) {
			retorno.setUnidade(unidadeTOBuilder.buildTO(p.getUnidade()));
		}
		if(Objects.nonNull(p.getFuncionario()) && Objects.nonNull(p.getFuncionario().getId())) {
			retorno.setFuncionario(funcionarioTOBuilder.buildTO(p.getFuncionario()));
		}
		if(Objects.nonNull(p.getDepartamento()) && Objects.nonNull(p.getDepartamento().getIdDepartamento())) {
			retorno.setDepartamento(departamentoTOBuilder.buildTO(p.getDepartamento()));
		}
		if(Objects.nonNull(p.getPrograma()) && Objects.nonNull(p.getPrograma().getId())) {
			retorno.setPrograma(programaTOBuilder.buildTO(p.getPrograma()));
		}
		if(Objects.nonNull(p.getProjeto()) && Objects.nonNull(p.getProjeto().getId())) {
			retorno.setProjeto(projetoTOBuilder.buildTO(p.getProjeto()));
		}
		
		retorno.setUsuarioAlteracao(p.getUsuarioAlteracao());
		
		
		return retorno;
	}

	public List<EstoquesTO> buildAll(List<Estoques> dtos) {
		return dtos.stream().map(dto -> buildTO(dto)).collect(Collectors.toList());
	}

	public EstoquesTO buildTOCombo(Estoques e) {
	EstoquesTO to = new EstoquesTO();
		
		if(Objects.isNull(e)) {
			return to;
		}
		
		BeanUtils.copyProperties(e, to);
		
		return to;
	
	}

}
