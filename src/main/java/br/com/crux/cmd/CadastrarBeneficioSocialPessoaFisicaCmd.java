package br.com.crux.cmd;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.BeneficioSocialPessoaFisicaTOBuilder;
import br.com.crux.dao.repository.BeneficioSocialPessoaFisicaRepository;
import br.com.crux.entity.BeneficioSocialPessoaFisica;
import br.com.crux.entity.PessoaFisica;
import br.com.crux.rule.CamposObrigatoriosBeneficioSocialPessoaFisicaRule;
import br.com.crux.to.BeneficioSocialPessoaFisicaTO;

@Component
public class CadastrarBeneficioSocialPessoaFisicaCmd {

	@Autowired private BeneficioSocialPessoaFisicaRepository repository;
	@Autowired private CamposObrigatoriosBeneficioSocialPessoaFisicaRule camposObrigatoriosRule;
	@Autowired private BeneficioSocialPessoaFisicaTOBuilder tOBuilder;
	
	
	public BeneficioSocialPessoaFisica cadastrar(PessoaFisica pessoaFisica, BeneficioSocialPessoaFisicaTO to) {
		camposObrigatoriosRule.verificar(pessoaFisica,to);

		BeneficioSocialPessoaFisica entity = tOBuilder.build(to);
		entity.setPessoaFisica(pessoaFisica);
		
		return repository.save(entity);
	}
	
	
	public void cadastrarLista(PessoaFisica pessoaFisica, List<BeneficioSocialPessoaFisicaTO> list) {
		if(Objects.nonNull(list)) {
			list.stream()
			     .map(item -> cadastrar(pessoaFisica, item))
			     .collect(Collectors.toList());
		}
	}
}
