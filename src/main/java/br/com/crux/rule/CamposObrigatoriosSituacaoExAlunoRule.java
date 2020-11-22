package br.com.crux.rule;

import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import br.com.crux.exception.CamposObrigatoriosException;
import br.com.crux.to.SituacaoExAlunoTO;

@Component
public class CamposObrigatoriosSituacaoExAlunoRule {

	public void verificar(SituacaoExAlunoTO to) {
		
		if(Objects.isNull(to.getAluno()) || Objects.isNull(to.getAluno().getId())) {
			throw new CamposObrigatoriosException("Operação não realizada. O campo aluno deve ser informado.");
		}
		
		if(StringUtils.isEmpty(to.getProfissao())) {
			throw new CamposObrigatoriosException("Operação não realizada. O campo profissão atual deve ser informado.");
		}

		if(StringUtils.isEmpty(to.getCondicaoAtual())) {
			throw new CamposObrigatoriosException("Operação não realizada. O campo condição atual deve ser informado.");
		}

		if(Objects.isNull(to.getDataAvaliacao())) {
			throw new CamposObrigatoriosException("Operação não realizada. O campo data da avaliação deve ser informado.");
		}
	}
}
