package br.com.crux.rule;

import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import br.com.crux.exception.CamposObrigatoriosException;
import br.com.crux.to.PessoaFisicaTO;

@Component
public class CamposObrigatoriosPessoaFisicaRule {
	
	public void verificar(PessoaFisicaTO to) {
		if(StringUtils.isEmpty(to.getNome())) {
			throw new CamposObrigatoriosException("Nome deve ser informado.");
		}

		if(Objects.isNull(to.getCpf())) {
			throw new CamposObrigatoriosException("CPF deve ser informado.");
		}
		
		if(StringUtils.isEmpty(to.getEndereco())) {
			throw new CamposObrigatoriosException("Endereço deve ser informado.");
		}
		
	}
}
