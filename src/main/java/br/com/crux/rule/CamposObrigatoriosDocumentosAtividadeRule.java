package br.com.crux.rule;

import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import br.com.crux.exception.CamposObrigatoriosException;
import br.com.crux.to.DocumentosAtividadeTO;

@Component
public class CamposObrigatoriosDocumentosAtividadeRule {

	

	public void verificar(DocumentosAtividadeTO to) {
		if(StringUtils.isEmpty(to.getDescricao())) {
			throw new CamposObrigatoriosException("Descrição deve ser informada.");
		}
		if(Objects.isNull(to.getAtividade())) {
			throw new CamposObrigatoriosException("Atividade deve ser informada.");
		}
	}
}
