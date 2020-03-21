package br.com.crux.to;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.format.annotation.NumberFormat;

public class ExtratoTO {

	private LocalDateTime dataAtual;
	private String numeroBanco;
	private String nomeBanco;
	private String numeroAgencia;
	private String numeroConta;

	private String periodoExtrato;
	
	@NumberFormat(pattern = "#,##0.00")
	private Double saldoAnterior;
	@NumberFormat(pattern = "#,##0.00")
	private Double saldoAtual;
	
	private String identificacaoConta;

	private List<Movimentacoes> movimentacoes;

	public LocalDateTime getDataAtual() {
		return dataAtual;
	}

	public void setDataAtual(LocalDateTime dataAtual) {
		this.dataAtual = dataAtual;
	}

	public String getNumeroBanco() {
		return numeroBanco;
	}

	public void setNumeroBanco(String numeroBanco) {
		this.numeroBanco = numeroBanco;
	}

	public String getNomeBanco() {
		return nomeBanco;
	}

	public void setNomeBanco(String nomeBanco) {
		this.nomeBanco = nomeBanco;
	}

	public String getNumeroAgencia() {
		return numeroAgencia;
	}

	public void setNumeroAgencia(String numeroAgencia) {
		this.numeroAgencia = numeroAgencia;
	}

	public String getNumeroConta() {
		return numeroConta;
	}

	public void setNumeroConta(String numeroConta) {
		this.numeroConta = numeroConta;
	}

	public String getPeriodoExtrato() {
		return periodoExtrato;
	}

	public void setPeriodoExtrato(String periodoExtrato) {
		this.periodoExtrato = periodoExtrato;
	}

	public Double getSaldoAnterior() {
		return saldoAnterior != null ? saldoAnterior : 0;
	}

	public void setSaldoAnterior(Double saldoAnterior) {
		this.saldoAnterior = saldoAnterior;
	}

	public Double getSaldoAtual() {
		return saldoAtual != null ? saldoAtual : 0;
	}

	public void setSaldoAtual(Double saldoAtual) {
		this.saldoAtual = saldoAtual;
	}

	public List<Movimentacoes> getMovimentacoes() {
		return movimentacoes;
	}

	public void setMovimentacoes(List<Movimentacoes> movimentacoes) {
		this.movimentacoes = movimentacoes;
	}

	public String getIdentificacaoConta() {
		identificacaoConta = "Banco: " + getNumeroBanco() + " - " + getNomeBanco() + " - Agência: " + getNumeroAgencia() + " - Conta: " + getNumeroConta();
		return identificacaoConta;
	}
	
	

}
