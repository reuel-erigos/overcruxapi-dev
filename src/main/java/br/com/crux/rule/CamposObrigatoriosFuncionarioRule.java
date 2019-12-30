package br.com.crux.rule;

import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import br.com.crux.exception.CamposObrigatoriosException;
import br.com.crux.exception.NotFoundException;
import br.com.crux.to.FuncionarioTO;

@Component
public class CamposObrigatoriosFuncionarioRule {

	public void verificar(FuncionarioTO to) {
		if (Objects.isNull(to.getDataAdmissao())) {
			throw new CamposObrigatoriosException("Data de Admissão deve ser informada.");
		}

		if (StringUtils.isEmpty(to.getMatricula())) {
			throw new CamposObrigatoriosException("Matrícula deve ser informada.");
		}

		if (Objects.isNull(to.getCargo())) {
			throw new CamposObrigatoriosException("Cargo deve ser informado.");
		}

		if (Objects.isNull(to.getUnidade())) {
			throw new CamposObrigatoriosException("Unidade deve ser informada.");
		}

		if (Objects.isNull(to.getEmpresaFuncionario())) {
			throw new NotFoundException("Empresa do Funcionário não informada.");
		}

	}
}
