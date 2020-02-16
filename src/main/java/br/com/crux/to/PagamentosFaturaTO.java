package br.com.crux.to;

import java.time.LocalDateTime;

public class PagamentosFaturaTO {

	private Long id;
	private FaturaTO fatura;
	private ContasBancariaTO contaBancaria;
	private SaldosContasBancariaTO saldoContaBancaria;
	private Double valorPagamento;
	private LocalDateTime dataPagamento;
	private String formaPagamento;
	private String numeroDocPagamento;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public FaturaTO getFatura() {
		return fatura;
	}
	public void setFatura(FaturaTO fatura) {
		this.fatura = fatura;
	}
	public ContasBancariaTO getContaBancaria() {
		return contaBancaria;
	}
	public void setContaBancaria(ContasBancariaTO contaBancaria) {
		this.contaBancaria = contaBancaria;
	}
	public SaldosContasBancariaTO getSaldoContaBancaria() {
		return saldoContaBancaria;
	}
	public void setSaldoContaBancaria(SaldosContasBancariaTO saldoContaBancaria) {
		this.saldoContaBancaria = saldoContaBancaria;
	}
	public Double getValorPagamento() {
		return valorPagamento;
	}
	public void setValorPagamento(Double valorPagamento) {
		this.valorPagamento = valorPagamento;
	}
	public LocalDateTime getDataPagamento() {
		return dataPagamento;
	}
	public void setDataPagamento(LocalDateTime dataPagamento) {
		this.dataPagamento = dataPagamento;
	}
	public String getFormaPagamento() {
		return formaPagamento;
	}
	public void setFormaPagamento(String formaPagamento) {
		this.formaPagamento = formaPagamento;
	}
	public String getNumeroDocPagamento() {
		return numeroDocPagamento;
	}
	public void setNumeroDocPagamento(String numeroDocPagamento) {
		this.numeroDocPagamento = numeroDocPagamento;
	}
	
}
