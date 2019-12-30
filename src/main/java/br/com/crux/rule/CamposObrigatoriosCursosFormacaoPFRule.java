package br.com.crux.rule;

import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import br.com.crux.exception.CamposObrigatoriosException;

@Component
public class CamposObrigatoriosCursosFormacaoPFRule {

	public void verificar(String nome,  Long idPessoaFisica) {
		
		if(StringUtils.isEmpty(nome)) {
			throw new CamposObrigatoriosException("Nome deve ser informado.");
		}

		if(Objects.isNull(idPessoaFisica)) {
			throw new CamposObrigatoriosException("Pessoa Física deve ser informada.");
		}
		
	}
}
