package br.com.crux.rule;

import java.util.Objects;

import org.springframework.stereotype.Component;

import br.com.crux.exception.CamposObrigatoriosException;
import br.com.crux.to.VulnerabilidadesAlunoTO;

@Component
public class CamposObrigatoriosVulnerabilidadesAlunoRule {

	public void verificar(VulnerabilidadesAlunoTO to) {
		if(Objects.isNull(to.getDataIdentificacao())) {
			throw new CamposObrigatoriosException("Data de Identificação deve ser informada.");
		}

		if(Objects.isNull(to.getSituacoesVulnerabilidade())) {
			throw new CamposObrigatoriosException("Situação de Vulnerabilidade deve ser informada.");
		}

		if(Objects.isNull(to.getSolucoes())) {
			throw new CamposObrigatoriosException("Solucao deve ser informada.");
		}
		
		if(Objects.isNull(to.getIdAluno())) {
			throw new CamposObrigatoriosException("Aluno deve ser informado.");
		}
		
	}
}
