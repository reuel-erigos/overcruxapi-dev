package br.com.crux.rule;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import br.com.crux.exception.CamposObrigatoriosException;
import br.com.crux.to.MovimentacoesMateriaisTO;

@Component
public class CamposObrigatoriosMovimentacoesMateriaisRule {

	public void verificar(MovimentacoesMateriaisTO to) {
		if (StringUtils.isEmpty(to.getTipoMovimentacao())) {
			throw new CamposObrigatoriosException("O tipo de movimentação deve ser informado.");
		}
		
	}
	
}
