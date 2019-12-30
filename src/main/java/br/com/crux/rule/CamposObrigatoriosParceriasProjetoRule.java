package br.com.crux.rule;

import java.util.Optional;

import org.springframework.stereotype.Component;

import br.com.crux.exception.CamposObrigatoriosException;
import br.com.crux.to.ParceriasProjetoTO;

@Component
public class CamposObrigatoriosParceriasProjetoRule {

	public void verificar(ParceriasProjetoTO novo) {
		Optional.ofNullable(novo.getEmpresa().getId()).orElseThrow(() -> new CamposObrigatoriosException("Empresa deve ser informada."));
		Optional.ofNullable(novo.getDescricaoTipoParceria()).orElseThrow(() -> new CamposObrigatoriosException("Tipo de parceria deve ser informada."));

	}
}
