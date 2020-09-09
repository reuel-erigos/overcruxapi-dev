package br.com.crux.rule;

import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Component;

import br.com.crux.exception.CamposObrigatoriosException;
import br.com.crux.to.ReembolsosPagamentosTO;

@Component
public class ValidarContaReembolsoRule {

	public void verificar(Long idContaBancaria, List<ReembolsosPagamentosTO> reembolsoPagamentosTO ) {
		if(Objects.nonNull(reembolsoPagamentosTO) && Objects.nonNull(idContaBancaria) ) {
			reembolsoPagamentosTO.stream()
			                  .filter(pag -> Objects.nonNull(pag.getContaBancaria()) && Objects.nonNull(pag.getContaBancaria().getId()))
			                  .forEach(pag -> {
				if(pag.getContaBancaria().getId().equals(idContaBancaria) ) {
					throw new CamposObrigatoriosException("A conta de reembolso não pode ser a mesma do movimento.");
				}
				
				if(pag.getData().toLocalDate().isBefore(pag.getData().toLocalDate())) {
					throw new CamposObrigatoriosException("A data do reembolso dos pagamento não pode ser menor que a data do pagamento.");
				}
			});
		}
	}
}
