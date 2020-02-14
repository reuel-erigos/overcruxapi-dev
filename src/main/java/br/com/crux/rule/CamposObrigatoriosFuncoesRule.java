package br.com.crux.rule;

import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import br.com.crux.exception.CamposObrigatoriosException;
import br.com.crux.to.FuncoesTO;

@Component
public class CamposObrigatoriosFuncoesRule {

	public void verificar(FuncoesTO to) {
		if (StringUtils.isEmpty(to.getNome())) {
			throw new CamposObrigatoriosException("O nome deve ser informado.");
		}

		if (Objects.isNull(to.getUnidade()) || Objects.isNull(to.getUnidade()
				.getIdUnidade())) {
			throw new CamposObrigatoriosException("A unidade deve ser informada.");
		}

		
		if (Objects.isNull(to.getValorSalario())) {
			throw new CamposObrigatoriosException("O valor deve ser informado.");
		}
	}
}
