package br.com.crux.rule;

import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Component;

import br.com.crux.exception.CamposObrigatoriosException;
import br.com.crux.to.RateiosPagamentosTO;

@Component
public class ValidarContaRateioPagamentoRule {

	public void verificar(Long idContaBancaria, List<RateiosPagamentosTO> rateiosPagamentosTO ) {
		if(Objects.nonNull(rateiosPagamentosTO) && Objects.nonNull(idContaBancaria) ) {
			rateiosPagamentosTO.stream()
			                  .filter(pag -> Objects.nonNull(pag.getContaBancaria()) && Objects.nonNull(pag.getContaBancaria().getId()))
			                  .forEach(pag -> {
				if(pag.getContaBancaria().getId().equals(idContaBancaria) ) {
					throw new CamposObrigatoriosException("A conta de rateio de pagamento não pode ser a mesma do movimento.");
				}
			});
		}
	}
}
