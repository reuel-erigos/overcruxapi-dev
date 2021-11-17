package br.com.crux.dao.dto;

import java.math.BigDecimal;

public class SaldoContaContabilDTO {
	
	private Double saldoDataInicioContaContabil;
	private Double saldoDataFimContaContabil;
	
	public SaldoContaContabilDTO() {
	}
	
	public SaldoContaContabilDTO(Object colunas) {
		this.saldoDataInicioContaContabil  = (colunas != null)? ((BigDecimal)colunas).doubleValue() : null;
		this.saldoDataFimContaContabil     = (colunas != null)? ((BigDecimal)colunas).doubleValue() : null;
	}

	
	public Double getSaldoDataInicioContaContabil() {
		return saldoDataInicioContaContabil;
	}

	public void setSaldoDataInicioContaContabil(Double saldoDataInicioContaContabil) {
		this.saldoDataInicioContaContabil = saldoDataInicioContaContabil;
	}

	public Double getSaldoDataFimContaContabil() {
		return saldoDataFimContaContabil;
	}

	public void setSaldoDataFimContaContabil(Double saldoDataFimContaContabil) {
		this.saldoDataFimContaContabil = saldoDataFimContaContabil;
	}

	public Double getSaldoContaContabil() {
		return saldoDataFimContaContabil;
	}

	public void setSaldoContaContabil(Double saldoContaContabil) {
		this.saldoDataFimContaContabil = saldoContaContabil;
	}


	

}
