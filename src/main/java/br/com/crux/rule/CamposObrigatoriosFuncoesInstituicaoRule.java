package br.com.crux.rule;

import java.util.Objects;

import org.springframework.stereotype.Component;

import br.com.crux.exception.CamposObrigatoriosException;
import br.com.crux.to.FuncoesInstituicaoTO;

@Component
public class CamposObrigatoriosFuncoesInstituicaoRule {

	public void verificar(FuncoesInstituicaoTO to) {

		if (Objects.isNull(to.getDataInicio())) {
			throw new CamposObrigatoriosException("Data de Início deve ser informada.");
		}

		if (Objects.isNull(to.getFuncionario()) && Objects.isNull(to.getFuncionario()
				.getId())) {
			throw new CamposObrigatoriosException("Funcionário deve ser informado.");
		}

		if (Objects.isNull(to.getFuncoes()) && Objects.isNull(to.getFuncoes()
				.getId())) {
			throw new CamposObrigatoriosException("Função deve ser informado.");
		}

	}
}
