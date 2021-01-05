package br.com.crux.rule;

import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import br.com.crux.entity.Unidade;
import br.com.crux.exception.CamposObrigatoriosException;
import br.com.crux.to.CertificadoUnidadeTO;

@Component
public class CamposObrigatoriosCertificadoUnidadeRule {

	public void verificar(CertificadoUnidadeTO to, Unidade unidade) {
		if(Objects.isNull(unidade)) {
			throw new CamposObrigatoriosException("Unidade deve ser informada.");
		}
		
		if(StringUtils.isEmpty(to.getCodigo())) {
			throw new CamposObrigatoriosException("Código do Certificado deve ser informado.");
		}

		if(Objects.isNull(to.getDataInicio())) {
			throw new CamposObrigatoriosException("Data do Início deve ser informada.");
		}
		
	}
}
