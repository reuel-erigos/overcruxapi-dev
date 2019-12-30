package br.com.crux.rule;

import java.util.Optional;

import org.springframework.stereotype.Component;

import br.com.crux.entity.Programa;
import br.com.crux.exception.CamposObrigatoriosException;
import br.com.crux.to.UnidadeTO;

@Component
public class CamposObrigatoriosProgramaUnidadesRule {

	public void verificar(UnidadeTO novo, Programa programa) {

		Optional.ofNullable(novo).orElseThrow(() -> new CamposObrigatoriosException("Unidade deve ser informada."));
		Optional.ofNullable(programa.getId()).orElseThrow(() -> new CamposObrigatoriosException("Programa deve ser informado."));

	}
}
