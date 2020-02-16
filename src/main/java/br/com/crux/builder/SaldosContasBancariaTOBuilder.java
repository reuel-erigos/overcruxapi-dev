package br.com.crux.builder;

import java.util.Objects;

import org.springframework.stereotype.Component;

import br.com.crux.entity.SaldosContasBancaria;
import br.com.crux.to.SaldosContasBancariaTO;

@Component
public class SaldosContasBancariaTOBuilder {

	public SaldosContasBancariaTO buildTO(SaldosContasBancaria saldosContasBancaria) {
		SaldosContasBancariaTO to = new SaldosContasBancariaTO();

		if (Objects.isNull(saldosContasBancaria)) {
			return to;
		}

		to.setId(saldosContasBancaria.getId());
		to.setDataAtualizacaoSaldo(saldosContasBancaria.getDataAtualizacaoSaldo());
		to.setDataSaldo(saldosContasBancaria.getDataSaldo());
		to.setDescricaoSaldo(saldosContasBancaria.getDescricaoSaldo());
		to.setValorSaldo(saldosContasBancaria.getValorSaldo());

		return to;
	}


}
