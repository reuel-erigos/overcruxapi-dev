package br.com.crux.rule;

import java.util.Objects;

import org.springframework.stereotype.Component;

import br.com.crux.exception.CamposObrigatoriosException;
import br.com.crux.to.MovimentacoesContabeisTO;

@Component
public class CamposObrigatoriosMovimentacoesContabeisRule {

	public void verificar(MovimentacoesContabeisTO to) {
		if(Objects.isNull(to.getDataMovimentacao())) {
			throw new CamposObrigatoriosException("A data da movimentação contábil deve ser informada.");
		}

		if(Objects.isNull(to.getValorMovimentacao())) {
			throw new CamposObrigatoriosException("O valor da movimentação contábil deve ser informado.");
		}

		if (Objects.isNull(to.getCategoriaOrigem01()) || Objects.isNull(to.getCategoriaOrigem01().getId())) {
			throw new CamposObrigatoriosException("A categoria de origem da movimentação 1 deve ser informada.");
		}
		if (Objects.isNull(to.getCategoriaDestino01()) || Objects.isNull(to.getCategoriaDestino01().getId())) {
			throw new CamposObrigatoriosException("A categoria de destino da movimentação 1 deve ser informada.");
		}
		
		/*
		if (Objects.isNull(to.getCategoriaOrigem02()) || Objects.isNull(to.getCategoriaOrigem02().getId())) {
			throw new CamposObrigatoriosException("A categoria de origem da movimentação 2 deve ser informada.");
		}
		if (Objects.isNull(to.getCategoriaDestino02()) || Objects.isNull(to.getCategoriaDestino02().getId())) {
			throw new CamposObrigatoriosException("A categoria de destino da movimentação 2 deve ser informada.");
		}
		*/

	}
}
