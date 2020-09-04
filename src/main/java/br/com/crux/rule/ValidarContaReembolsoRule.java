package br.com.crux.rule;

import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Component;

import br.com.crux.exception.CamposObrigatoriosException;
import br.com.crux.to.PagamentosFaturaTO;

@Component
public class ValidarContaReembolsoRule {

	public void verificar(Long idContaBancaria, List<PagamentosFaturaTO> pagamentosFaturaTO ) {
		if(Objects.nonNull(pagamentosFaturaTO) && Objects.nonNull(idContaBancaria) ) {
			pagamentosFaturaTO.stream()
			                  .filter(pag -> Objects.nonNull(pag.getContaReembolso()) && Objects.nonNull(pag.getContaReembolso().getId()))
			                  .forEach(pag -> {
				if(pag.getContaReembolso().getId().equals(idContaBancaria) ) {
					throw new CamposObrigatoriosException("A conta de reembolso não pode ser a mesma do movimento.");
				}
				
				if(pag.getDataReembolso().toLocalDate().isBefore(pag.getDataPagamento().toLocalDate())) {
					throw new CamposObrigatoriosException("A data do reembolso dos pagamento não pode ser menor que a data do pagamento.");
				}
			});
		}
	}
}
