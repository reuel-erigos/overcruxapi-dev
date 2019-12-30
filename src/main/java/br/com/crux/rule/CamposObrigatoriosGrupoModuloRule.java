package br.com.crux.rule;

import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import br.com.crux.exception.CamposObrigatoriosException;
import br.com.crux.to.GrupoModuloTO;

@Component
public class CamposObrigatoriosGrupoModuloRule {

	public void verificar(GrupoModuloTO to) {

		if (StringUtils.isEmpty(to.getNome())) {
			throw new CamposObrigatoriosException("Nome deve ser informado.");
		}
		
		if (Objects.isNull(to.getPerfilAcesso())) {
			throw new CamposObrigatoriosException("Perfil de acesso deve ser informado.");
		}
		
		if (Objects.isNull(to.getModulo())) {
			throw new CamposObrigatoriosException("Módulo deve ser informado.");
		}

		if (Objects.isNull(to.getUnidade())) {
			throw new CamposObrigatoriosException("Unidade deve ser informada.");
		}

	}
}
