package br.com.crux.to.relatorios.financeiro;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

import br.com.crux.infra.util.DataUtil;

public class SaldoProjetoDTO {
	
	private String tipoTransacao;
	private String descricao;
	private String numeroDocumento;
	private String dataDocumento;
	private Double valor;
	private String banco;
	private String conta;
	private Double saldo;
	
	public SaldoProjetoDTO() {
	}
	
	public SaldoProjetoDTO(Object[] colunas) {
		this.tipoTransacao       = (colunas[0] != null) ? (String) colunas[0] : "";
		this.descricao           = (colunas[1] != null) ? (String) colunas[1] : "";
		this.numeroDocumento     = (colunas[2] != null) ? (String) colunas[2] : "";
		this.dataDocumento       = (colunas[3] != null) ? DataUtil.formataData(new Date(((Timestamp) colunas[3]).getTime()), "dd/MM/yyyy") : "";
		this.valor               = (colunas[4] != null)? ((BigDecimal)colunas[4]).doubleValue() : null;
		this.banco               = (colunas[5] != null) ? (String) colunas[5] : "";
		this.conta               = (colunas[6] != null) ? (String) colunas[6] : "";
		this.saldo               = (colunas[7] != null)? ((BigDecimal)colunas[7]).doubleValue() : null;
	}

	public String getTipoTransacao() {
		return tipoTransacao;
	}

	public void setTipoTransacao(String tipoTransacao) {
		this.tipoTransacao = tipoTransacao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getNumeroDocumento() {
		return numeroDocumento;
	}

	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	public String getDataDocumento() {
		return dataDocumento;
	}

	public void setDataDocumento(String dataDocumento) {
		this.dataDocumento = dataDocumento;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public String getBanco() {
		return banco;
	}

	public void setBanco(String banco) {
		this.banco = banco;
	}

	public String getConta() {
		return conta;
	}

	public void setConta(String conta) {
		this.conta = conta;
	}

	public Double getSaldo() {
		return saldo;
	}

	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}

	
}
