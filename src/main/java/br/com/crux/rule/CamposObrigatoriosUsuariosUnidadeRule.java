package br.com.crux.rule;

import java.util.Optional;

import org.springframework.stereotype.Component;

import br.com.crux.exception.CamposObrigatoriosException;
import br.com.crux.to.UsuariosUnidadesTO;

@Component
public class CamposObrigatoriosUsuariosUnidadeRule {

	public void verificar(UsuariosUnidadesTO usuarioUnidade) {

		Optional.ofNullable(usuarioUnidade).orElseThrow(() -> new CamposObrigatoriosException("Usuário do Sistema deve ser informado."));
		Optional.ofNullable(usuarioUnidade.getUnidade()).orElseThrow(() -> new CamposObrigatoriosException("Unidade deve ser informado."));

	}
}
