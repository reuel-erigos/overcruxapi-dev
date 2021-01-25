package br.com.crux.rule;

import org.springframework.stereotype.Component;

import br.com.crux.entity.PessoaFisica;
import br.com.crux.exception.CamposObrigatoriosException;
import br.com.crux.to.BeneficioSocialPessoaFisicaTO;

@Component
public class CamposObrigatoriosBeneficioSocialPessoaFisicaRule {

	public void verificar(PessoaFisica pessoaFisica,
			BeneficioSocialPessoaFisicaTO to) {

		if (to.getBeneficioSocial() == null
				|| to.getBeneficioSocial().getId() == null) {
			throw new CamposObrigatoriosException(
					"O campo benefício social deve ser informado.");
		}

		if (pessoaFisica == null || pessoaFisica.getId() == null) {
			throw new CamposObrigatoriosException("Nenhuma pessoa informada.");
		}
		
		if(to.getDataInicio() == null) {
			throw new CamposObrigatoriosException("Data de início deve ser informada.");
		}

	}
}
