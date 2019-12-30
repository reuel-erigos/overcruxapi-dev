package br.com.crux.rule;

import java.util.Objects;

import org.springframework.stereotype.Component;

import br.com.crux.exception.CamposObrigatoriosException;
import br.com.crux.to.ColaboradoresTurmaTO;

@Component
public class CamposObrigatoriosColaboradoresTurmaRule {

	public void verificar(ColaboradoresTurmaTO to) {

		if(Objects.isNull(to.getDataEntradaTurma())) {
			new CamposObrigatoriosException("Data de entrada deve ser informada.");
		}

		if(Objects.isNull(to.getFuncionario())) {
			new CamposObrigatoriosException("Funcionário deve ser informado.");
		}

		if(Objects.isNull(to.getCargo())) {
			new CamposObrigatoriosException("Cargo deve ser informado.");
		}

		if(Objects.isNull(to.getIdTurma())) {
			new CamposObrigatoriosException("Turma deve ser informada.");
		}
		
	}
}
