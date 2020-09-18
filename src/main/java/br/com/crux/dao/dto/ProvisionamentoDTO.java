package br.com.crux.dao.dto;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;

public class ProvisionamentoDTO {
	
	private Long id;
	private Long seq;
	private String tipo;
	private String situacao;
	private String numeroDocumento;
	private LocalDate dataPagamentoRecebimento;
	private String banco;
	private String apropriacaoFinanceira;
	private String fornecedor;
	private String complementoHistorico;
	private String nomeProjetoPrograma;
	private String grupoContas;
	private Double valor;
	
	public ProvisionamentoDTO() {
	}
	
	public ProvisionamentoDTO(Object[] colunas) {
		this.id                       = (colunas[0] != null)? ((BigDecimal)colunas[0]).longValue() : null;
		this.seq                      = (colunas[1] != null)? ((BigDecimal)colunas[1]).longValue() : null;
		this.tipo                     = (String) colunas[2];
		this.situacao                 = (String) colunas[3];
		this.numeroDocumento          = (String) colunas[4];
		this.dataPagamentoRecebimento = (colunas[5] != null)? ((Timestamp)colunas[5]).toLocalDateTime().toLocalDate() : null;
		this.banco                    = (String) colunas[6];
		this.apropriacaoFinanceira    = (String) colunas[7];
		this.fornecedor               = (String) colunas[8];
		this.complementoHistorico     = (String) colunas[9];
		this.nomeProjetoPrograma      = (String) colunas[10];
		this.grupoContas              = (String) colunas[11];
		this.valor                    = (colunas[12] != null)? ((BigDecimal)colunas[12]).doubleValue() : null;
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getSeq() {
		return seq;
	}

	public void setSeq(Long seq) {
		this.seq = seq;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getSituacao() {
		return situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

	public String getNumeroDocumento() {
		return numeroDocumento;
	}

	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	public LocalDate getDataPagamentoRecebimento() {
		return dataPagamentoRecebimento;
	}

	public void setDataPagamentoRecebimento(LocalDate dataPagamentoRecebimento) {
		this.dataPagamentoRecebimento = dataPagamentoRecebimento;
	}

	public String getBanco() {
		return banco;
	}

	public void setBanco(String banco) {
		this.banco = banco;
	}

	public String getApropriacaoFinanceira() {
		return apropriacaoFinanceira;
	}

	public void setApropriacaoFinanceira(String apropriacaoFinanceira) {
		this.apropriacaoFinanceira = apropriacaoFinanceira;
	}

	public String getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(String fornecedor) {
		this.fornecedor = fornecedor;
	}

	public String getComplementoHistorico() {
		return complementoHistorico;
	}

	public void setComplementoHistorico(String complementoHistorico) {
		this.complementoHistorico = complementoHistorico;
	}

	public String getNomeProjetoPrograma() {
		return nomeProjetoPrograma;
	}

	public void setNomeProjetoPrograma(String nomeProjetoPrograma) {
		this.nomeProjetoPrograma = nomeProjetoPrograma;
	}

	public String getGrupoContas() {
		return grupoContas;
	}

	public void setGrupoContas(String grupoContas) {
		this.grupoContas = grupoContas;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

}
