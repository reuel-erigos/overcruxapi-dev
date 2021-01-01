package br.com.crux.builder;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.cmd.GetUnidadeLogadaCmd;
import br.com.crux.cmd.GetUsuarioLogadoCmd;
import br.com.crux.entity.BeneficioSocial;
import br.com.crux.to.BeneficioSocialTO;

@Component
public class BeneficioSocialTOBuilder {

	@Autowired private GetUsuarioLogadoCmd getUsuarioLogadoCmd;
	@Autowired private GetUnidadeLogadaCmd getUnidadeLogadaCmd;

	public BeneficioSocial build(BeneficioSocialTO to) {
		BeneficioSocial retorno = new BeneficioSocial();

		BeanUtils.copyProperties(to, retorno);
		
		Long idInstituicao = getUnidadeLogadaCmd.getUnidadeTO().getInstituicao().getId();
		retorno.setIdInstituicao(idInstituicao);
		retorno.setUsuarioAlteracao(getUsuarioLogadoCmd.getUsuarioLogado().getIdUsuario());

		return retorno;
	}

	public BeneficioSocialTO buildTO(BeneficioSocial p) {
		BeneficioSocialTO retorno = new BeneficioSocialTO();
		
		if(Objects.isNull(p)) {
			return retorno;
		}
		
		BeanUtils.copyProperties(p, retorno);

		return retorno;
	}

	public List<BeneficioSocialTO> buildAllTO(List<BeneficioSocial> dtos) {
		return dtos.stream().map(dto -> buildTO(dto)).collect(Collectors.toList());
	}

}
