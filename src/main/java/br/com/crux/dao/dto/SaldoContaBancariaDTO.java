package br.com.crux.dao.dto;

import java.math.BigDecimal;

public class SaldoContaBancariaDTO {
	
	private Double saldoInicial;
	private Double saldoFinal;
	
	public SaldoContaBancariaDTO() {
	}
	
	public SaldoContaBancariaDTO(Object[] colunas) {
		this.saldoInicial      = (colunas[0] != null)? ((BigDecimal)colunas[0]).doubleValue() : null;
		this.saldoFinal        = (colunas[1] != null)? ((BigDecimal)colunas[1]).doubleValue() : null;
	}

	public Double getSaldoInicial() {
		return saldoInicial;
	}

	public void setSaldoInicial(Double saldoInicial) {
		this.saldoInicial = saldoInicial;
	}

	public Double getSaldoFinal() {
		return saldoFinal;
	}

	public void setSaldoFinal(Double saldoFinal) {
		this.saldoFinal = saldoFinal;
	}


}
