package br.com.crux.rule;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import br.com.crux.exception.CamposObrigatoriosException;

@Component
public class CamposObrigatoriosEmpresaRule {

	public void verificar(String codigo, String nomeRazaoSocial, String tipoEmpresa, String telefone, String endereco) {
		if(StringUtils.isEmpty(codigo)) {
			throw new CamposObrigatoriosException("Código deve ser informado.");
		}
		if(StringUtils.isEmpty(nomeRazaoSocial)) {
			throw new CamposObrigatoriosException("Nome razão social deve ser informado.");
		}
		if(StringUtils.isEmpty(telefone)) {
			throw new CamposObrigatoriosException("Telefone deve ser informado.");
		}
		if(StringUtils.isEmpty(endereco)) {
			throw new CamposObrigatoriosException("Endereco deve ser informado.");
		}
		
		if(StringUtils.isEmpty(tipoEmpresa)) {
			throw new CamposObrigatoriosException("Tipo da Empresa deve ser informado.");
		}
		
	}
}
