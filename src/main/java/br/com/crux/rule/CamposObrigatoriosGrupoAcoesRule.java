package br.com.crux.rule;

import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import br.com.crux.exception.CamposObrigatoriosException;
import br.com.crux.to.GrupoAcoesTO;

@Component
public class CamposObrigatoriosGrupoAcoesRule {

	public void verificar(GrupoAcoesTO to) {
		if (StringUtils.isEmpty(to.getNumeroGrupo())) {
			throw new CamposObrigatoriosException("Período deve ser informado.");
		}

		if (Objects.isNull(to.getAtividade()) && Objects.isNull(to.getAtividade().getId())) {
			throw new CamposObrigatoriosException("Atividade deve ser informada.");
		}

	}
}
