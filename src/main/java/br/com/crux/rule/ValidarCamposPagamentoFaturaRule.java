package br.com.crux.rule;

import java.time.LocalDate;
import java.util.Objects;

import org.springframework.stereotype.Component;

import br.com.crux.exception.CamposObrigatoriosException;
import br.com.crux.to.PagamentosFaturaTO;

@Component
public class ValidarCamposPagamentoFaturaRule {


	public void verificar(PagamentosFaturaTO to) {

		if(Objects.nonNull(to.getDataPagamento()) && to.getDataPagamento().toLocalDate().isAfter(LocalDate.now())) {
			throw new CamposObrigatoriosException("A data de pagamento da fatura não pode ser maior que hoje.");
		}
		
		
	}
}
