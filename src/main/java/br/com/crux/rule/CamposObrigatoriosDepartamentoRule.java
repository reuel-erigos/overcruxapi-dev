package br.com.crux.rule;

import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import br.com.crux.exception.CamposObrigatoriosException;
import br.com.crux.to.DepartamentoTO;

@Component
public class CamposObrigatoriosDepartamentoRule {


	public void verificar(DepartamentoTO to) {
		if(StringUtils.isEmpty(to.getNmDepartamento())) {
			throw new CamposObrigatoriosException("O nome do departamento deve ser informado.");
		}
		
		if(StringUtils.isEmpty(to.getCdUnidadeDepartamento())) {
			throw new CamposObrigatoriosException("Sigla deve ser informada.");
		}
		
		if(Objects.isNull(to.getUnidade()) || Objects.isNull(to.getUnidade().getIdUnidade())) {
			throw new CamposObrigatoriosException("A unidade deve ser informada.");
		}
	}
}
