package br.com.crux.rule;

import java.util.Objects;

import org.springframework.stereotype.Component;

import br.com.crux.exception.CamposObrigatoriosException;

@Component
public class CamposObrigatoriosEncaminhaAlunosRule {

	public void verificar(Long idEntidadeSocial, Long idAluno) {
		if(Objects.isNull(idEntidadeSocial)) {
			throw new CamposObrigatoriosException("intidade Social deve ser informada.");
		}
		if(Objects.isNull(idAluno)) {
			throw new CamposObrigatoriosException("Aluno deve ser informado.");
		}
		
	}
}
