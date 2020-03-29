package br.com.crux.to;

import java.time.LocalDateTime;
import java.util.List;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.springframework.format.annotation.NumberFormat;

import br.com.crux.infra.adapter.LocalDateTimeAdapter;

public class ExtratoTO {

	@XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
	private LocalDateTime dataAtual;
	
	private String numeroBanco;
	private String nomeBanco;
	private String numeroAgencia;
	private String numeroConta;
	private String periodoExtrato;
	private String identificacaoConta;
	private boolean isSaldoAtualDivergente;
	
	@NumberFormat(pattern = "#,##0.00")
	private Double saldoAnterior;
	@NumberFormat(pattern = "#,##0.00")
	private Double saldoAtual;
	
	private List<MovimentosExtratoTO> movimentacoes;

	
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

	public List<MovimentosExtratoTO> getMovimentacoes() {
		return movimentacoes;
	}

	public void setMovimentacoes(List<MovimentosExtratoTO> movimentacoes) {
		this.movimentacoes = movimentacoes;
	}

	public String getIdentificacaoConta() {
		identificacaoConta = "Banco: " + getNumeroBanco() + " - " + getNomeBanco() + " - Agência: " + getNumeroAgencia() + " - Conta: " + getNumeroConta();
		return identificacaoConta;
	}

	public boolean getIsSaldoAtualDivergente() {
		return isSaldoAtualDivergente;
	}

	public void setIsSaldoAtualDivergente(boolean isSaldoAtualDivergente) {
		this.isSaldoAtualDivergente = isSaldoAtualDivergente;
	}
	
	

}