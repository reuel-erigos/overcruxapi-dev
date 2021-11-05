package br.com.crux.cmd;

import br.com.crux.builder.BeneficioSocialPessoaFisicaTOBuilder;
import br.com.crux.dao.repository.BeneficioSocialPessoaFisicaRepository;
import br.com.crux.entity.BeneficioSocialPessoaFisica;
import br.com.crux.entity.PessoaFisica;
import br.com.crux.to.BeneficioSocialPessoaFisicaTO;
import br.com.crux.to.BeneficioSocialTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AlterarListaBeneficioSocialCmd {

	@Autowired private BeneficioSocialPessoaFisicaTOBuilder toBuilder;
	@Autowired private BeneficioSocialPessoaFisicaRepository repository;
	@Autowired private CadastrarBeneficioSocialCmd cadastrarCmd;
	

	protected BeneficioSocialPessoaFisicaTO getTO(BeneficioSocialPessoaFisica entity) {
		return toBuilder.buildTO(entity);
	}

	protected List<BeneficioSocialPessoaFisicaTO> getTOListaBanco(List<BeneficioSocialPessoaFisica> lista) {
		return toBuilder.buildAllTO(lista);
	}

	protected List<BeneficioSocialPessoaFisica> getListaBanco(PessoaFisica pai) {
		return repository.findByPessoaFisica(pai.getId()).orElse(new ArrayList<BeneficioSocialPessoaFisica>());
	}

	protected Long getIdentificadorTO(BeneficioSocialPessoaFisicaTO to) {
		return to.getId();
	}


	protected void cadastrar(BeneficioSocialTO to) {
		cadastrarCmd.cadastrar(to);
	}

	protected void deletar(BeneficioSocialPessoaFisica registro) {
		repository.delete(registro);
	}

}
