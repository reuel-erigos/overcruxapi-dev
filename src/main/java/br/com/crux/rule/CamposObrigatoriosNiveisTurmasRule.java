package br.com.crux.rule;

import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import br.com.crux.exception.CamposObrigatoriosException;
import br.com.crux.to.NiveisTurmasTO;

@Component
public class CamposObrigatoriosNiveisTurmasRule {


	public void verificar(NiveisTurmasTO to) {
		if(Objects.isNull(to.getCodigo())) {
			throw new CamposObrigatoriosException("O código deve ser informado.");
		}
		
		if(StringUtils.isEmpty(to.getDescricao())) {
			throw new CamposObrigatoriosException("Descrição deve ser informada.");
		}
	}
}
