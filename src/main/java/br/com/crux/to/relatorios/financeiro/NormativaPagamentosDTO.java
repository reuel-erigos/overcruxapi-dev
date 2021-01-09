package br.com.crux.to.relatorios.financeiro;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

import br.com.crux.infra.util.DataUtil;

public class NormativaPagamentosDTO {
	
	private String nomeProgramaProjeto;
	private String descFornecedor;
	private String numeroDocumento;
	private String cnpjCpf;
	private String dataDocumento;
	private Double valorMovimentacao;
	private String numeroDocumentoPagamento;
	private String dataPagamento;
	private String nomeCategoria;
	private Double valorPagamento;
	private String dataVencimentoProxima;
	
	public NormativaPagamentosDTO() {
	}
	
	public NormativaPagamentosDTO(Object[] colunas) {
		this.nomeProgramaProjeto       = (colunas[0] != null) ? (String) colunas[0] : "";
		this.descFornecedor            = (colunas[1] != null) ? (String) colunas[1] : "";
		this.numeroDocumento           = (colunas[2] != null) ? (String) colunas[2] : "";
		this.cnpjCpf                   = (colunas[3] != null) ? (String) colunas[3] : "";
		this.dataDocumento             = (colunas[4] != null) ? (String) colunas[4] : "";
		this.valorMovimentacao         = (colunas[5] != null)? ((BigDecimal)colunas[5]).doubleValue() : null; 
		this.numeroDocumentoPagamento  = (colunas[6] != null) ? (String) colunas[6] : "";
		this.dataPagamento             = (colunas[7] != null) ? DataUtil.formataData(new Date(((Timestamp) colunas[7]).getTime()), "dd/MM/yyyy") : "";
		this.nomeCategoria             = (colunas[8] != null) ? (String) colunas[8] : "";
		this.valorPagamento            = (colunas[9] != null)? ((BigDecimal)colunas[9]).doubleValue() : null;
		this.dataVencimentoProxima     = (colunas[10] != null) ? DataUtil.formataData(new Date(((Timestamp) colunas[10]).getTime()), "dd/MM/yyyy") : "";
		
	}

	public String getNomeProgramaProjeto() {
		return nomeProgramaProjeto;
	}

	public void setNomeProgramaProjeto(String nomeProgramaProjeto) {
		this.nomeProgramaProjeto = nomeProgramaProjeto;
	}

	public String getDescFornecedor() {
		return descFornecedor;
	}

	public void setDescFornecedor(String descFornecedor) {
		this.descFornecedor = descFornecedor;
	}

	public String getNumeroDocumento() {
		return numeroDocumento;
	}

	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	public String getCnpjCpf() {
		return cnpjCpf;
	}

	public void setCnpjCpf(String cnpjCpf) {
		this.cnpjCpf = cnpjCpf;
	}

	public String getDataDocumento() {
		return dataDocumento;
	}

	public void setDataDocumento(String dataDocumento) {
		this.dataDocumento = dataDocumento;
	}

	public Double getValorMovimentacao() {
		return valorMovimentacao;
	}

	public void setValorMovimentacao(Double valorMovimentacao) {
		this.valorMovimentacao = valorMovimentacao;
	}

	public String getNumeroDocumentoPagamento() {
		return numeroDocumentoPagamento;
	}

	public void setNumeroDocumentoPagamento(String numeroDocumentoPagamento) {
		this.numeroDocumentoPagamento = numeroDocumentoPagamento;
	}

	public String getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(String dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

	public String getNomeCategoria() {
		return nomeCategoria;
	}

	public void setNomeCategoria(String nomeCategoria) {
		this.nomeCategoria = nomeCategoria;
	}

	public Double getValorPagamento() {
		return valorPagamento;
	}

	public void setValorPagamento(Double valorPagamento) {
		this.valorPagamento = valorPagamento;
	}

	public String getDataVencimentoProxima() {
		return dataVencimentoProxima;
	}

	public void setDataVencimentoProxima(String dataVencimentoProxima) {
		this.dataVencimentoProxima = dataVencimentoProxima;
	}

	
}
