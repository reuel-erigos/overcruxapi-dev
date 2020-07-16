package br.com.crux.rule;

import java.util.Optional;

import org.springframework.stereotype.Component;

import br.com.crux.exception.CamposObrigatoriosException;
import br.com.crux.to.CadastroReservaAtividadeTO;

@Component
public class CamposObrigatoriosCadastroReservaAtividadeRule {

	public void verificar(CadastroReservaAtividadeTO to) {
		Optional.ofNullable(to.getAtividade()).orElseThrow(() -> new CamposObrigatoriosException("Atividade deve ser informada."));
		Optional.ofNullable(to.getNomeInteressado()).orElseThrow(() -> new CamposObrigatoriosException("Nome do interessado deve ser informado."));
	}
}
