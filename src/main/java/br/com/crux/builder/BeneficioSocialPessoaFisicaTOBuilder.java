package br.com.crux.builder;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.cmd.GetBeneficioSocialCmd;
import br.com.crux.cmd.GetPessoaFisicaCmd;
import br.com.crux.cmd.GetUnidadeLogadaCmd;
import br.com.crux.cmd.GetUsuarioLogadoCmd;
import br.com.crux.entity.BeneficioSocialPessoaFisica;
import br.com.crux.to.BeneficioSocialPessoaFisicaTO;

@Component
public class BeneficioSocialPessoaFisicaTOBuilder {

	@Autowired private GetUsuarioLogadoCmd getUsuarioLogadoCmd;
	@Autowired private GetUnidadeLogadaCmd getUnidadeLogadaCmd;
	@Autowired private GetBeneficioSocialCmd getBeneficioSocialCmd;
	@Autowired private GetPessoaFisicaCmd getPessoaFisicaCmd;
	@Autowired private BeneficioSocialTOBuilder beneficioSocialTOBuilder;
	@Autowired private PessoaFisicaTOBuilder pessoaFisicaTOBuilder;


	public BeneficioSocialPessoaFisica build(BeneficioSocialPessoaFisicaTO to) {
		BeneficioSocialPessoaFisica retorno = new BeneficioSocialPessoaFisica();

		BeanUtils.copyProperties(to, retorno);

		if(to.getBeneficioSocial() != null && to.getBeneficioSocial().getId() != null) {
			retorno.setBeneficioSocial(getBeneficioSocialCmd.getById(to.getBeneficioSocial().getId()));
		}

		if(to.getPessoaFisica() != null && to.getPessoaFisica().getId() != null) {
			retorno.setPessoaFisica(getPessoaFisicaCmd.getById(to.getPessoaFisica().getId()));
		}
		
		Long idInstituicao = getUnidadeLogadaCmd.getUnidadeTO().getInstituicao().getId();
		retorno.setIdInstituicao(idInstituicao);
		retorno.setUsuarioAlteracao(getUsuarioLogadoCmd.getUsuarioLogado().getIdUsuario());

		
		
		return retorno;
	}

	public BeneficioSocialPessoaFisicaTO buildTO(BeneficioSocialPessoaFisica p) {
		BeneficioSocialPessoaFisicaTO retorno = new BeneficioSocialPessoaFisicaTO();
		
		if(Objects.isNull(p)) {
			return retorno;
		}
		
		BeanUtils.copyProperties(p, retorno);
		
		retorno.setBeneficioSocial(beneficioSocialTOBuilder.buildTO(p.getBeneficioSocial()));
		retorno.setPessoaFisica(pessoaFisicaTOBuilder.buildTO(p.getPessoaFisica()));

		return retorno;
	}

	public List<BeneficioSocialPessoaFisicaTO> buildAllTO(List<BeneficioSocialPessoaFisica> dtos) {
		return dtos.stream().map(dto -> buildTO(dto)).collect(Collectors.toList());
	}

}
