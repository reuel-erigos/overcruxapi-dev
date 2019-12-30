package br.com.crux.rule;

import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import br.com.crux.exception.CamposObrigatoriosException;
import br.com.crux.to.OcorrenciaAlunoTO;

@Component
public class CamposObrigatoriosOcorrenciaAlunoRule {

	public void verificar(OcorrenciaAlunoTO to) {
		
		if(Objects.isNull(to.getAluno())) {
			throw new CamposObrigatoriosException("Aluno deve ser informado.");
		}

		if(Objects.isNull(to.getFuncionario())) {
			throw new CamposObrigatoriosException("Funcionário deve ser informado.");
		}
		
		if(Objects.isNull(to.getTipoOcorrenciaAluno())) {
			throw new CamposObrigatoriosException("Tipo de ocorrência deve ser informado.");
		}
		
		if(Objects.isNull(to.getDataOcorrencia())) {
			throw new CamposObrigatoriosException("Data da ocorrência deve ser informada.");
		}

		if (StringUtils.isEmpty(to.getDescricao())) {
			throw new CamposObrigatoriosException("Descrição da ocorrência deve ser informada.");
		}

	}
}
