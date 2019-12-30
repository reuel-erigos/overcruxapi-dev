package br.com.crux.rule;

import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import br.com.crux.exception.CamposObrigatoriosException;
import br.com.crux.to.AcoesCompetenciaTO;

@Component
public class CamposObrigatoriosAcoesCompentenciaRule {


	public void verificar(AcoesCompetenciaTO to) {
		if(Objects.isNull(to.getTalentosPf())) {
			throw new CamposObrigatoriosException("Talento do Funcionário deve ser informado.");
		}
		
		if(StringUtils.isEmpty(to.getDescricao())) {
			throw new CamposObrigatoriosException("Descrição deve ser informada.");
		}
		
	}
}
