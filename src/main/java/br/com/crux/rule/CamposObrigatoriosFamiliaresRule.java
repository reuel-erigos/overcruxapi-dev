package br.com.crux.rule;

import java.util.Objects;

import org.springframework.stereotype.Component;

import br.com.crux.exception.CamposObrigatoriosException;
import br.com.crux.to.FamiliaresTO;

@Component
public class CamposObrigatoriosFamiliaresRule {

	public void verificar(FamiliaresTO to) {
		if(Objects.isNull(to.getDataCadastro())) {
			throw new CamposObrigatoriosException("Data de Início deve ser informada.");
		}
		
		if(Objects.isNull(to.getPessoasFisica())) {
			throw new CamposObrigatoriosException("Pessoa Física deve ser informada.");
		}
		
		if(Objects.isNull(to.getAluno())) {
			throw new CamposObrigatoriosException("Aluno deve ser informado.");
		}
		
	}
}
