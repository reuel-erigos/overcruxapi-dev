package br.com.crux.builder;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.cmd.GetCategoriasContabeisCmd;
import br.com.crux.cmd.GetUnidadeLogadaCmd;
import br.com.crux.dao.dto.EmpresaDTO;
import br.com.crux.entity.CategoriasContabeis;
import br.com.crux.entity.Empresa;
import br.com.crux.enums.CategoriaEmpresa;
import br.com.crux.enums.TipoEmpresa;
import br.com.crux.to.ComboEmpresaTO;
import br.com.crux.to.EmpresaTO;

@Component
public class EmpresaTOBuilder {
	
	@Autowired private GetUnidadeLogadaCmd getUnidadeLogadaCmd;
	@Autowired private GetCategoriasContabeisCmd getCategoriasContabeisCmd;
	@Autowired private CategoriasContabeisTOBuilder categoriasContabeisTOBuilder;

	
	public Empresa build(EmpresaTO p) {
		Empresa retorno = new Empresa();

		if (Objects.isNull(p)) {
			return retorno;
		}
		
		retorno.setId(p.getId());
		retorno.setCodigo(p.getCodigo());
		retorno.setNomeFantasia(p.getNomeFantasia());
		retorno.setNomeRazaoSocial(p.getNomeRazaoSocial());
		retorno.setCnpj(p.getCnpj());
		retorno.setInscricaoEstadual(p.getInscricaoEstadual());
		retorno.setInscricaoMunicipal(p.getInscricaoMunicipal());
		retorno.setAtiva(p.getAtiva());
		
		if(StringUtils.isNotEmpty(p.getTipoEmpresa())) {
			retorno.setTipoEmpresa(TipoEmpresa.getPorTipo(p.getTipoEmpresa()));
		}
		
		retorno.setValorIcms(p.getValorIcms());
		retorno.setDescricaoCategoriaEmpresa(p.getDescricaoCategoriaEmpresa());
		retorno.setDescricaoTipoEmpresa(p.getDescricaoTipoEmpresa());
		
		if(StringUtils.isNotEmpty(p.getTipoEmpresa())) {
			retorno.setCategoriaEmpresa(CategoriaEmpresa.getPorTipo(p.getCategoriaEmpresa()));
		}
		
		retorno.setTelefone(p.getTelefone());
		retorno.setEmail(p.getEmail());
		
		retorno.setAutorizaEmail(p.getAutorizaEmail());
		
		retorno.setHomePage(p.getHomePage());
		retorno.setEndereco(p.getEndereco());
		retorno.setBairro(p.getBairro());
		retorno.setCidade(p.getCidade());
		retorno.setCep(p.getCep());
		retorno.setUf(p.getUf());
		retorno.setUsuarioAlteracao(p.getUsuarioAlteracao());

		retorno.setIdInstituicao(getUnidadeLogadaCmd.getUnidadeTO().getInstituicao().getId());
		
		if (Objects.nonNull(p.getCategoria()) && Objects.nonNull(p.getCategoria().getId())) {
			CategoriasContabeis categoria = getCategoriasContabeisCmd.getById(p.getCategoria().getId());
			retorno.setCategoria(categoria);
		} else {
			retorno.setCategoria(null);
		}

		
		return retorno;
	}

	public EmpresaTO buildTO(Empresa p) {
		EmpresaTO retorno = new EmpresaTO();

		if (Objects.isNull(p)) {
			return retorno;
		}

		retorno.setId(p.getId());
		retorno.setCodigo(p.getCodigo());
		retorno.setNomeFantasia(p.getNomeFantasia());
		retorno.setNomeRazaoSocial(p.getNomeRazaoSocial());
		retorno.setCnpj(p.getCnpj());
		retorno.setInscricaoEstadual(p.getInscricaoEstadual());
		retorno.setInscricaoMunicipal(p.getInscricaoMunicipal());
		retorno.setAtiva(p.getAtiva());
		
		if(Objects.nonNull(p.getTipoEmpresa())) {
			retorno.setTipoEmpresa(p.getTipoEmpresa().getTipo());
		}

		retorno.setValorIcms(p.getValorIcms());
		retorno.setDescricaoCategoriaEmpresa(p.getDescricaoCategoriaEmpresa());
		retorno.setDescricaoTipoEmpresa(p.getDescricaoTipoEmpresa());
		retorno.setCategoriaEmpresa(Objects.nonNull(p.getCategoriaEmpresa()) ? p.getCategoriaEmpresa().getTipo() : null);
		retorno.setTelefone(p.getTelefone());
		retorno.setEmail(p.getEmail());
		retorno.setAutorizaEmail(p.getAutorizaEmail());
		retorno.setHomePage(p.getHomePage());
		retorno.setEndereco(p.getEndereco());
		retorno.setBairro(p.getBairro());
		retorno.setCidade(p.getCidade());
		retorno.setCep(p.getCep());
		retorno.setUf(p.getUf());
		retorno.setUsuarioAlteracao(p.getUsuarioAlteracao());
		retorno.setIdInstituicao(p.getIdInstituicao());

		if(Objects.nonNull(p.getCategoria())) {
			retorno.setCategoria(categoriasContabeisTOBuilder.buildTO(p.getCategoria()));
		}
		
		return retorno;
	}

	public List<EmpresaTO> buildAll(List<Empresa> dtos) {
		return dtos.stream().map(dto -> buildTO(dto)).collect(Collectors.toList());
	}

	public List<EmpresaTO> buildAllCombo(List<Empresa> dtos) {
		return dtos.stream().map(dto -> buildTOCombo(dto)).collect(Collectors.toList());
	}

	public EmpresaTO buildTOCombo(Empresa p) {
		EmpresaTO retorno = new EmpresaTO();

		if (Objects.isNull(p)) {
			return retorno;
		}

		retorno.setId(p.getId());
		retorno.setCodigo(p.getCodigo());
		retorno.setNomeFantasia(p.getNomeFantasia());
		retorno.setNomeRazaoSocial(p.getNomeRazaoSocial());
		retorno.setCnpj(p.getCnpj());
		
		return retorno;
	}
	
	public ComboEmpresaTO buildComboEmpresaTO(EmpresaDTO p) {
		ComboEmpresaTO retorno = new ComboEmpresaTO();
		
		if(Objects.isNull(p)) {
			return retorno;
		}
		
		BeanUtils.copyProperties(p, retorno);
		
		return retorno;
	}

	public List<ComboEmpresaTO> buildAllComboDTO(List<EmpresaDTO> dtos) {
		return dtos.stream().map(dto -> buildComboEmpresaTO(dto)).collect(Collectors.toList());
		
	}
	

}
