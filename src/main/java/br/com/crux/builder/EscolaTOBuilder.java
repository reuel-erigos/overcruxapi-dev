package br.com.crux.builder;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.entity.Escola;
import br.com.crux.entity.Instituicao;
import br.com.crux.entity.UsuariosSistema;
import br.com.crux.to.EscolaTO;
import br.com.crux.to.InstituicaoTO;
import br.com.crux.to.UsuariosSistemaTO;

@Component
public class EscolaTOBuilder {
	
	@Autowired private RegiaoAdministrativaTOBuilder regiaoAdministrativaTOBuilder; 

	public Escola build(EscolaTO p) {
		Escola retorno = new Escola();
		
		retorno.setId(p.getId());
		retorno.setBairro(p.getBairro());
		retorno.setCelular(p.getCelular());
		retorno.setCep(p.getCep());
		retorno.setCidade(p.getCidade());
		retorno.setCodigo(p.getCodigo());
		retorno.setComplemento(p.getComplemento());
		retorno.setEmail(p.getEmail());
		retorno.setEndereco(p.getEndereco());
		retorno.setEtapaEnsino(p.getEtapaEnsino());
		retorno.setHomePage(p.getHomePage());
		retorno.setNome(p.getNome());
		retorno.setTelefone(p.getTelefone());
		retorno.setTipo(p.getTipo());
		retorno.setTipoLocalidade(p.getTipoLocalidade());
		retorno.setUf(p.getUf());
		if(p.getInstituicao() != null) {
			retorno.setInstituicao(new Instituicao());
			retorno.getInstituicao().setId(p.getInstituicao().getId());
		}
		if(p.getUsuarioSistema() != null) {
			retorno.setUsuarioSistema(new UsuariosSistema());
			retorno.getUsuarioSistema().setIdUsuario(p.getUsuarioSistema().getId());
		}
		if(p.getRegiaoAdministrativa() != null) {
			retorno.setRegiaoAdministrativa(regiaoAdministrativaTOBuilder.build(p.getRegiaoAdministrativa()));
		}
		

		return retorno;
	}

	public EscolaTO buildTO(Escola p) {
		EscolaTO retorno = new EscolaTO();

		if (Objects.isNull(p)) {
			return retorno;
		}

		retorno.setId(p.getId());
		retorno.setBairro(p.getBairro());
		retorno.setCelular(p.getCelular());
		retorno.setCep(p.getCep());
		retorno.setCidade(p.getCidade());
		retorno.setCodigo(p.getCodigo());
		retorno.setComplemento(p.getComplemento());
		retorno.setEmail(p.getEmail());
		retorno.setEndereco(p.getEndereco());
		retorno.setEtapaEnsino(p.getEtapaEnsino());
		retorno.setHomePage(p.getHomePage());
		retorno.setNome(p.getNome());
		retorno.setTelefone(p.getTelefone());
		retorno.setTipo(p.getTipo());
		retorno.setTipoLocalidade(p.getTipoLocalidade());
		retorno.setUf(p.getUf());
		if(p.getInstituicao() != null) {
			retorno.setInstituicao(new InstituicaoTO());
			retorno.getInstituicao().setId(p.getInstituicao().getId());
		}
		if(p.getUsuarioSistema() != null) {
			retorno.setUsuarioSistema(new UsuariosSistemaTO());
			retorno.getUsuarioSistema().setId(p.getUsuarioSistema().getIdUsuario());
		}
		if(p.getRegiaoAdministrativa() != null) {
			retorno.setRegiaoAdministrativa(regiaoAdministrativaTOBuilder.buildTO(p.getRegiaoAdministrativa()));
		}
		
		return retorno;
	}
	
	public EscolaTO buildComboTO(Escola p) {
		EscolaTO retorno = new EscolaTO();

		if (Objects.isNull(p)) {
			return retorno;
		}

		retorno.setId(p.getId());
		retorno.setNome(p.getNome());
		retorno.setTipo(p.getTipo());
		retorno.setUf(p.getUf());
		if(p.getRegiaoAdministrativa() != null) {
			retorno.setRegiaoAdministrativa(regiaoAdministrativaTOBuilder.buildTO(p.getRegiaoAdministrativa()));
		}
		return retorno;
	}

	public List<EscolaTO> buildAllCombo(List<Escola> dtos) {
		return dtos.stream().map(dto -> buildComboTO(dto)).collect(Collectors.toList());
	}

}
