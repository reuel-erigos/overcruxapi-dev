package br.com.crux.rule;

import java.util.Objects;

import org.springframework.stereotype.Component;

import br.com.crux.exception.CamposObrigatoriosException;
import br.com.crux.to.EntidadesSociaisTO;

@Component
public class CamposObrigatoriosEntidadesSociaisRule {

	public void verificar(EntidadesSociaisTO to) {
		if(Objects.isNull(to.getEmpresa())) {
			throw new CamposObrigatoriosException("Empresa deve ser informada.");
		}
	}
}
