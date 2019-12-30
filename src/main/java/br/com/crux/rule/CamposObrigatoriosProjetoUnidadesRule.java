package br.com.crux.rule;

import java.util.Optional;

import org.springframework.stereotype.Component;

import br.com.crux.entity.Projeto;
import br.com.crux.exception.CamposObrigatoriosException;
import br.com.crux.to.UnidadeTO;

@Component
public class CamposObrigatoriosProjetoUnidadesRule {

	public void verificar(UnidadeTO novo, Projeto projeto) {
	
		Optional.ofNullable(novo).orElseThrow(() -> new CamposObrigatoriosException("Unidade deve ser informada."));
		Optional.ofNullable(projeto.getId()).orElseThrow(() -> new CamposObrigatoriosException("Projeto deve ser informado."));

	}
}
