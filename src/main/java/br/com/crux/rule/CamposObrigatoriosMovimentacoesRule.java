package br.com.crux.rule;

import java.util.Objects;

import org.springframework.stereotype.Component;

import br.com.crux.exception.CamposObrigatoriosException;
import br.com.crux.to.MovimentacoesTO;

@Component
public class CamposObrigatoriosMovimentacoesRule {

	public void verificar(MovimentacoesTO to) {

		if (Objects.isNull(to.getStTipoMovimentacao())) {
			throw new CamposObrigatoriosException("O tipo movimentação deve ser informada.");
		}

		if (Objects.isNull(to.getUnidade()) || Objects.isNull(to.getUnidade()
				.getIdUnidade())) {
			throw new CamposObrigatoriosException("A unidade deve ser informada.");
		}

		if (Objects.isNull(to.getDepartamento()) || Objects.isNull(to.getDepartamento()
				.getIdDepartamento())) {
			throw new CamposObrigatoriosException("O Departamento deve ser informado.");
		}

	}
}
