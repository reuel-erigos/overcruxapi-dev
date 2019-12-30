package br.com.crux.rule;

import java.time.LocalDateTime;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import br.com.crux.exception.CamposObrigatoriosException;

@Component
public class CamposObrigatoriosObjetivoRule {

	public void verificar(String nome, LocalDateTime dataImplantacao, Long idPerspectiva) {
		if(StringUtils.isEmpty(nome)) {
			throw new CamposObrigatoriosException("O nome do departamento deve ser informado.");
		}
		
		if(Objects.isNull(dataImplantacao)) {
			throw new CamposObrigatoriosException("Data de implantação deve ser informada.");
		}

		if(Objects.isNull(idPerspectiva)) {
			throw new CamposObrigatoriosException("Perspectiva deve ser informada.");
		}
		
	}
}
