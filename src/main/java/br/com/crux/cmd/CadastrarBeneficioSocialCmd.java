package br.com.crux.cmd;

import br.com.crux.builder.BeneficioSocialPessoaFisicaTOBuilder;
import br.com.crux.builder.BeneficioSocialTOBuilder;
import br.com.crux.dao.repository.BeneficioSocialPessoaFisicaRepository;
import br.com.crux.dao.repository.BeneficioSocialRepository;
import br.com.crux.entity.BeneficioSocial;
import br.com.crux.entity.BeneficioSocialPessoaFisica;
import br.com.crux.entity.PessoaFisica;
import br.com.crux.rule.CamposObrigatoriosBeneficioSocialPessoaFisicaRule;
import br.com.crux.rule.CamposObrigatoriosBeneficioSocialRule;
import br.com.crux.to.AlunoTO;
import br.com.crux.to.BeneficioSocialPessoaFisicaTO;
import br.com.crux.to.BeneficioSocialTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class CadastrarBeneficioSocialCmd {

	@Autowired private BeneficioSocialRepository repository;
	@Autowired private CamposObrigatoriosBeneficioSocialRule camposObrigatoriosRule;
	@Autowired private BeneficioSocialTOBuilder tOBuilder;

	public BeneficioSocialTO cadastrar(BeneficioSocialTO to) {
		camposObrigatoriosRule.verificar(to.toString());

		BeneficioSocial entity = tOBuilder.build(to);

		BeneficioSocialTO beneficioSocialTOSalvo = tOBuilder.buildTO(repository.save(entity));

		return beneficioSocialTOSalvo;
	}
}
