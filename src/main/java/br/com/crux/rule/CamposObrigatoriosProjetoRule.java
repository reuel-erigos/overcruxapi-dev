package br.com.crux.rule;

import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import br.com.crux.exception.CamposObrigatoriosException;
import br.com.crux.to.ProjetoTO;

@Component
public class CamposObrigatoriosProjetoRule {

	public void verificar(ProjetoTO to) {
		if (StringUtils.isEmpty(to.getNome())) {
			throw new CamposObrigatoriosException("Nome deve ser informado.");
		}
		if (Objects.isNull(to.getDataPrevisaoInicio())) {
			throw new CamposObrigatoriosException("Data de Início da Previsão deve ser informada.");
		}

	}
}
