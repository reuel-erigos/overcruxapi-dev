package br.com.crux.rule;

import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import br.com.crux.exception.CamposObrigatoriosException;
import br.com.crux.to.FaltasFuncionarioTO;

@Component
public class CamposObrigatoriosFaltasFuncionarioRule {

	public void verificar(FaltasFuncionarioTO to) {
		if (StringUtils.isEmpty(to.getDescricao())) {
			throw new CamposObrigatoriosException("Descrição deve ser informada.");
		}

		if (Objects.isNull(to.getFuncionarioFaltou()) || Objects.isNull(to.getFuncionarioFaltou().getId())) {
			throw new CamposObrigatoriosException("Funcionário que faltou deve ser informado.");
		}

		if (Objects.isNull(to.getFuncionarioCadastrouFalta()) || Objects.isNull(to.getFuncionarioCadastrouFalta().getId())) {
			throw new CamposObrigatoriosException("Funcionário do cadastro deve ser informado.");
		}

	}

	public void verificarID(FaltasFuncionarioTO to) {
		if (Objects.isNull(to.getId())) {
			throw new CamposObrigatoriosException("Id não informado.");
		}

	}

}
