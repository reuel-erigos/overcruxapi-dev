package br.com.crux.cmd;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.BeneficioSocialPessoaFisicaTOBuilder;
import br.com.crux.dao.repository.BeneficioSocialPessoaFisicaRepository;
import br.com.crux.entity.BeneficioSocialPessoaFisica;
import br.com.crux.to.BeneficioSocialPessoaFisicaTO;

@Component
public class GetBeneficioSocialPessoaFisicaCmd {


	@Autowired private BeneficioSocialPessoaFisicaRepository repository;
	@Autowired private BeneficioSocialPessoaFisicaTOBuilder toBuilder;
	
	
	public List<BeneficioSocialPessoaFisica> getAllPorPessoaFisica(Long idPessoa) {
		List<BeneficioSocialPessoaFisica> retorno = new ArrayList<BeneficioSocialPessoaFisica>();
		
		Optional<List<BeneficioSocialPessoaFisica>> beneficiosSocias = repository.findByPessoaFisica((idPessoa));
		if(!beneficiosSocias.isPresent()) {return new ArrayList<BeneficioSocialPessoaFisica>();}
		
		beneficiosSocias.get().stream().forEach(r -> retorno.add(r));
		return retorno;
		
	}
	
	public List<BeneficioSocialPessoaFisicaTO> getAllPorPessoaFisicaTO(Long idPessoa) {
		List<BeneficioSocialPessoaFisicaTO> retorno = new ArrayList<BeneficioSocialPessoaFisicaTO>();
		
		List<BeneficioSocialPessoaFisica> beneficiosSocias = getAllPorPessoaFisica(idPessoa);
		beneficiosSocias.stream().forEach(r -> retorno.add(toBuilder.buildTO(r)));
		
		return retorno;
	}	
}
