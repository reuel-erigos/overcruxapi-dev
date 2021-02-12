package br.com.crux.rule;

import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.cmd.GetUnidadeLogadaCmd;
import br.com.crux.dao.repository.PessoaFisicaRepository;
import br.com.crux.entity.PessoaFisica;
import br.com.crux.exception.PessoaFisicaJaExisteException;

@Component
public class ValidarDuplicidadeCPFRule {

	@Autowired private PessoaFisicaRepository pessoaFisicaRepository ;
	@Autowired private GetUnidadeLogadaCmd getUnidadeLogadaCmd;
	
	public void verificar(String cpf, Long id) {
		Long idInstituicao = getUnidadeLogadaCmd.getUnidadeTO().getInstituicao().getId();
		if(StringUtils.isNotEmpty(cpf)) {
			Optional<PessoaFisica> pessoaFisica = pessoaFisicaRepository.findByCpfAndInstituicao(cpf, idInstituicao);
			if(pessoaFisica.isPresent()) {
				if(!pessoaFisica.get().getId().equals(id)) {
					throw new PessoaFisicaJaExisteException("Já existe um registro com esse CPF.");
				}
			}
		}
		
	}

}
