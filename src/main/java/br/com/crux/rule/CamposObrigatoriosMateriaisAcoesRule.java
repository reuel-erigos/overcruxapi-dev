package br.com.crux.rule;

import java.util.Objects;

import org.springframework.stereotype.Component;

import br.com.crux.exception.CamposObrigatoriosException;
import br.com.crux.to.MateriaisAcoesTO;

@Component
public class CamposObrigatoriosMateriaisAcoesRule {


	public void verificar(MateriaisAcoesTO to) {

		if(Objects.isNull(to.getIdAcao())) {
			throw new CamposObrigatoriosException("Ação deve ser informada.");
		}
		
		if(Objects.isNull(to.getMaterial())) {
			throw new CamposObrigatoriosException("Material deve ser informado.");
		}
		
	}
}
