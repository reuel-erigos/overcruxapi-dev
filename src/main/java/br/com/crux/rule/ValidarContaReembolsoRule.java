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
			pagamentosFaturaTO.stream().forEach(pag -> {
				if(pag.getContaReembolso().getId().equals(idContaBancaria) ) {
					throw new CamposObrigatoriosException("A conta de reembolso não pode ser a mesma do movimento.");
				}
			});
		}
	}
}