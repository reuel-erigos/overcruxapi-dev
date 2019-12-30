package br.com.crux.rule;

import java.util.Objects;

import org.springframework.stereotype.Component;

import br.com.crux.exception.CamposObrigatoriosException;
import br.com.crux.to.AvaliacoesAlunosTO;

@Component
public class CamposObrigatoriosAvaliacoesAlunosRule {

	public void verificar(Long idAtividade, Long idAvaliacao) {
		if(Objects.isNull(idAtividade)) {
			throw new CamposObrigatoriosException("Atividade deve ser informada.");
		}
		if(Objects.isNull(idAvaliacao)) {
			throw new CamposObrigatoriosException("Avaliacao deve ser informada.");
		}
		
	}

	public void verificar(AvaliacoesAlunosTO to) {
		if(Objects.isNull(to.getAtividadesAluno()) || Objects.isNull(to.getAtividadesAluno().getId())) {
			throw new CamposObrigatoriosException("Atividade deve ser informada.");
		}
		if(Objects.isNull(to.getAvaliacoes()) || Objects.isNull(to.getAvaliacoes().getId())) {
			throw new CamposObrigatoriosException("Avaliacao deve ser informada.");
		}
		
	}
}
