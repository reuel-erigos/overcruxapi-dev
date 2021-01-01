package br.com.crux.cmd;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.BeneficioSocialPessoaFisicaTOBuilder;
import br.com.crux.dao.repository.BeneficioSocialPessoaFisicaRepository;
import br.com.crux.entity.BeneficioSocialPessoaFisica;
import br.com.crux.entity.PessoaFisica;
import br.com.crux.to.BeneficioSocialPessoaFisicaTO;

@Component
public class AlterarListaBeneficioSocialPessoaFisicaCmd extends AbstractAlterarListaCmd<BeneficioSocialPessoaFisica, BeneficioSocialPessoaFisicaTO, PessoaFisica> {

	@Autowired private BeneficioSocialPessoaFisicaTOBuilder toBuilder;
	@Autowired private BeneficioSocialPessoaFisicaRepository repository;
	@Autowired private CadastrarBeneficioSocialPessoaFisicaCmd cadastrarCmd;
	
	@Override
	protected BeneficioSocialPessoaFisicaTO getTO(BeneficioSocialPessoaFisica entity) {
		return toBuilder.buildTO(entity);
	}

	@Override
	protected List<BeneficioSocialPessoaFisicaTO> getTOListaBanco(List<BeneficioSocialPessoaFisica> lista) {
		return toBuilder.buildAllTO(lista);
	}

	@Override
	protected List<BeneficioSocialPessoaFisica> getListaBanco(PessoaFisica pai) {
		return repository.findByPessoaFisica(pai.getId()).orElse(new ArrayList<BeneficioSocialPessoaFisica>());
	}

	@Override
	protected Long getIdentificadorTO(BeneficioSocialPessoaFisicaTO to) {
		return to.getId();
	}

	@Override
	protected void cadastrar(BeneficioSocialPessoaFisicaTO to, PessoaFisica p) {
		cadastrarCmd.cadastrar(p, to);
	}

	@Override
	protected void deletar(BeneficioSocialPessoaFisica registro) {
		repository.delete(registro);

	}

}
