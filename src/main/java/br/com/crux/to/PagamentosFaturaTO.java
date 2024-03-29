package br.com.crux.to;

import java.time.LocalDateTime;
import java.util.List;

public class PagamentosFaturaTO {

	private Long id;
	private Long idFatura;
	private ContasBancariaTO contaBancaria;
	private SaldosContasBancariaTO saldoContaBancaria;
	private Double valorPagamento;
	private LocalDateTime dataPagamento;
	private String formaPagamento;
	private String numeroDocPagamento;
	private Double valorMulta;
	private Double valorJuros;
	private Boolean statusRegistroSaldo;

	private String descricao;
	
	private Double valorDesconto;
	
	private List<RateiosPagamentosTO> rateioPagamento;
	private List<ReembolsosPagamentosTO> reembolsos;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdFatura() {
		return idFatura;
	}
	public void setIdFatura(Long idFatura) {
		this.idFatura = idFatura;
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
	public Double getValorMulta() {
		return valorMulta;
	}
	public void setValorMulta(Double valorMulta) {
		this.valorMulta = valorMulta;
	}
	public Double getValorJuros() {
		return valorJuros;
	}
	public void setValorJuros(Double valorJuros) {
		this.valorJuros = valorJuros;
	}
	public Boolean getStatusRegistroSaldo() {
		return statusRegistroSaldo;
	}
	public void setStatusRegistroSaldo(Boolean statusRegistroSaldo) {
		this.statusRegistroSaldo = statusRegistroSaldo;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public List<RateiosPagamentosTO> getRateioPagamento() {
		return rateioPagamento;
	}
	public void setRateioPagamento(List<RateiosPagamentosTO> rateioPagamento) {
		this.rateioPagamento = rateioPagamento;
	}
	public List<ReembolsosPagamentosTO> getReembolsos() {
		return reembolsos;
	}
	public void setReembolsos(List<ReembolsosPagamentosTO> reembolsos) {
		this.reembolsos = reembolsos;
	}
	public Double getValorDesconto() {
		return valorDesconto;
	}
	public void setValorDesconto(Double valorDesconto) {
		this.valorDesconto = valorDesconto;
	}
}
