package br.com.crux.builder;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.cmd.GetUnidadeLogadaCmd;
import br.com.crux.entity.Empresa;
import br.com.crux.enums.CategoriaEmpresa;
import br.com.crux.enums.TipoEmpresa;
import br.com.crux.to.EmpresaTO;

@Component
public class EmpresaTOBuilder {
	
	@Autowired private GetUnidadeLogadaCmd getUnidadeLogadaCmd;

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

}
