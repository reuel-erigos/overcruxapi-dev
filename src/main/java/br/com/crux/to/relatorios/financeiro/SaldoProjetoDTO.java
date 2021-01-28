package br.com.crux.to.relatorios.financeiro;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

import br.com.crux.infra.util.DataUtil;

public class SaldoProjetoDTO {
	
	private String nomeProgramaProjeto;
	private String tipoTransacao;
	private String descricao;
	private String numeroDocumento;
	private String dataOperacao;
	private Double valorOperacao;
	private String banco;
	private String conta;
	private String parceiro;
	private Double saldo;
	
	
	public SaldoProjetoDTO() {
	}
	
	public SaldoProjetoDTO(Object[] colunas) {
		this.nomeProgramaProjeto = (colunas[0] != null) ? (String) colunas[0] : "";
		this.tipoTransacao       = (colunas[1] != null) ? (String) colunas[1] : "";
		this.descricao           = (colunas[2] != null) ? (String) colunas[2] : "";
		this.numeroDocumento     = (colunas[3] != null) ? (String) colunas[3] : "";
		this.dataOperacao        = (colunas[4] != null) ? DataUtil.formataData(new Date(((Timestamp) colunas[4]).getTime()), "dd/MM/yyyy") : "";
		this.valorOperacao       = (colunas[5] != null)? ((BigDecimal)colunas[5]).doubleValue() : null;
		this.banco               = (colunas[6] != null) ? (String) colunas[6] : "";
		this.conta               = (colunas[7] != null) ? (String) colunas[7] : "";
		this.parceiro            = (colunas[8] != null) ? (String) colunas[8] : "";
		this.saldo               = (colunas[9] != null)? ((BigDecimal)colunas[9]).doubleValue() : null;
	}

	public String getNomeProgramaProjeto() {
		return nomeProgramaProjeto;
	}

	public void setNomeProgramaProjeto(String nomeProgramaProjeto) {
		this.nomeProgramaProjeto = nomeProgramaProjeto;
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

	public String getDataOperacao() {
		return dataOperacao;
	}

	public void setDataOperacao(String dataOperacao) {
		this.dataOperacao = dataOperacao;
	}

	public Double getValorOperacao() {
		return valorOperacao;
	}

	public void setValorOperacao(Double valorOperacao) {
		this.valorOperacao = valorOperacao;
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

	public String getParceiro() {
		return parceiro;
	}

	public void setParceiro(String parceiro) {
		this.parceiro = parceiro;
	}

	public Double getSaldo() {
		return saldo;
	}

	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}


	
}
