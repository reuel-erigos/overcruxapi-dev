package br.com.crux.rule;

import java.util.Objects;

import org.springframework.stereotype.Component;

import br.com.crux.exception.CamposObrigatoriosException;
import br.com.crux.to.AlocacoesFuncionarioTO;

@Component
public class CamposObrigatoriosAlocacoesFuncionarioRule {

	public void verificar(AlocacoesFuncionarioTO to) {

		if (Objects.isNull(to.getFuncionario())) {
			throw new CamposObrigatoriosException("Funcionário deve ser informado.");
		}

		if (Objects.isNull(to.getDataInicioVinculacao())) {
			throw new CamposObrigatoriosException("Data de início deve ser informada.");
		}
		
		if (Objects.isNull(to.getDataFimVinculacao())) {
			throw new CamposObrigatoriosException("Data de fim deve ser informada.");
		}
		
		if(Objects.isNull(to.getPrograma()) && Objects.isNull(to.getProjeto())) {
			throw new CamposObrigatoriosException("Programa ou Projeto deve ser informado.");
		}
		
	}
}
