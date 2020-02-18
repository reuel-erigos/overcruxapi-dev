package br.com.crux.builder;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.entity.SaldosContasBancaria;
import br.com.crux.to.SaldosContasBancariaTO;

@Component
public class SaldosContasBancariaTOBuilder {
	
	@Autowired
	private ContasBancariaTOBuilder contasBancariaTOBuilder;

	public SaldosContasBancariaTO buildTO(SaldosContasBancaria p) {
		SaldosContasBancariaTO to = new SaldosContasBancariaTO();

		if (Objects.isNull(p)) {
			return to;
		}

		to.setId(p.getId());
		to.setDataAtualizacaoSaldo(p.getDataAtualizacaoSaldo());
		to.setDataSaldo(p.getDataSaldo());
		to.setDescricaoSaldo(p.getDescricaoSaldo());
		to.setValorSaldo(p.getValorSaldo());
		to.setContaBancaria(contasBancariaTOBuilder.buildTO(p.getContaBancaria()));

		return to;
	}
	
	
	public SaldosContasBancaria build(SaldosContasBancariaTO p) {
		SaldosContasBancaria to = new SaldosContasBancaria();

		to.setId(p.getId());
		to.setDataAtualizacaoSaldo(p.getDataAtualizacaoSaldo());
		to.setDataSaldo(p.getDataSaldo());
		to.setDescricaoSaldo(p.getDescricaoSaldo());
		to.setValorSaldo(p.getValorSaldo());
		to.setContaBancaria(contasBancariaTOBuilder.build(p.getContaBancaria()));

		return to;
	}
	
	
	public List<SaldosContasBancariaTO> buildAll(List<SaldosContasBancaria> dtos) {
		return dtos.stream().map(dto -> buildTO(dto)).collect(Collectors.toList());
	}


}
