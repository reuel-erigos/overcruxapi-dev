package br.com.crux.dao.dto;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;

public class ConciliacaoDTO {
	
	private Long id;
	private Long seq;
	private String tipo;
	private String situacao;
	private String numeroDocumento;
	private LocalDate data;
	private String banco;
	private String categoria;
	private String fornecedor;
	private String complemento;
	private String centroCusto;
	private String grupoContas;
	private Double valor;
	
	public ConciliacaoDTO() {
	}
	
	public ConciliacaoDTO(Object[] colunas) {
		this.id                       = (colunas[0] != null)? ((BigDecimal)colunas[0]).longValue() : null;
		this.seq                      = (colunas[1] != null)? ((BigDecimal)colunas[1]).longValue() : null;
		this.tipo                     = (String) colunas[2];
		this.situacao                 = (String) colunas[3];
		this.numeroDocumento          = (String) colunas[4];
		this.data                     = (colunas[5] != null)? ((Timestamp)colunas[5]).toLocalDateTime().toLocalDate() : null;
		this.banco                    = (String) colunas[6];
		this.categoria                = (String) colunas[7];
		this.fornecedor               = (String) colunas[8];
		this.complemento              = (String) colunas[9];
		this.centroCusto              = (String) colunas[10];
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

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public String getBanco() {
		return banco;
	}

	public void setBanco(String banco) {
		this.banco = banco;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(String fornecedor) {
		this.fornecedor = fornecedor;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getCentroCusto() {
		return centroCusto;
	}

	public void setCentroCusto(String centroCusto) {
		this.centroCusto = centroCusto;
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
