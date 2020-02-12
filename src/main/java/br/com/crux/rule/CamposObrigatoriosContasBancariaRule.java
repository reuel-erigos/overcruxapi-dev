package br.com.crux.rule;

import java.util.Objects;

import org.springframework.stereotype.Component;

import br.com.crux.exception.CamposObrigatoriosException;
import br.com.crux.to.ContasBancariaTO;

@Component
public class CamposObrigatoriosContasBancariaRule {

	public void verificar(ContasBancariaTO to) {
		if (Objects.isNull(to.getUnidade()) || Objects.isNull(to.getUnidade()
				.getIdUnidade())) {
			throw new CamposObrigatoriosException("A unidade deve ser informada.");
		}

	}
}
