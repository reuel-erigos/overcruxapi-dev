package br.com.crux.rule;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import br.com.crux.exception.CamposObrigatoriosException;
import br.com.crux.to.TipoOcorrenciaAlunoTO;

@Component
public class CamposObrigatoriosTipoOcorrenciaAlunoRule {

	public void verificar(TipoOcorrenciaAlunoTO to) {
		if (StringUtils.isEmpty(to.getTipoOcorrencia())) {
			throw new CamposObrigatoriosException("O tipo de ocorrência deve ser informado.");
		}

	}
}
