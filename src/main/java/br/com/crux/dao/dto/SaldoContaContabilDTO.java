package br.com.crux.dao.dto;

import java.math.BigDecimal;

public class SaldoContaContabilDTO {
	
	private Double saldoContaContabil;
	
	public SaldoContaContabilDTO() {
	}
	
	public SaldoContaContabilDTO(Object colunas) {
		this.saldoContaContabil  = (colunas != null)? ((BigDecimal)colunas).doubleValue() : null;
	}

	public Double getSaldoContaContabil() {
		return saldoContaContabil;
	}

	public void setSaldoContaContabil(Double saldoContaContabil) {
		this.saldoContaContabil = saldoContaContabil;
	}


	

}
