package br.com.crux.rule;

import java.util.Objects;

import org.springframework.stereotype.Component;

import br.com.crux.exception.CamposObrigatoriosException;
import br.com.crux.to.VulnerabilidadesFamiliarTO;

@Component
public class CamposObrigatoriosVulnerabilidadesFamiliarRule {

	public void verificar(VulnerabilidadesFamiliarTO to) {
		if(Objects.isNull(to.getDataIdentificacao())) {
			throw new CamposObrigatoriosException("Data de Identificação deve ser informada.");
		}

		if(Objects.isNull(to.getSituacoesVulnerabilidade())) {
			throw new CamposObrigatoriosException("Vulnerabilidade deve ser informada.");
		}

		if(Objects.isNull(to.getSolucoes())) {
			throw new CamposObrigatoriosException("Solucao deve ser informada.");
		}
		
		if(Objects.isNull(to.getFamiliar())) {
			throw new CamposObrigatoriosException("Familiar deve ser informada.");
		}
		
	}
}
