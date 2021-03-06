package br.com.crux.dao.dto;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;

public class ProvisionamentoDTO {
	
	private Long id;
	private String situacao;
	private String numeroDocumento;
	private LocalDate data;
	private Double valor;
	private String complemento;
	private String categoria;
	private String centroCusto;
	private String grupoContas;
	private String descricaoFornecedor;
	private String nomeFornecedor;
	private Boolean semDocumento;
	
	public ProvisionamentoDTO() {
	}
	
	public ProvisionamentoDTO(Object[] colunas) {
		this.id                       = (colunas[0] != null)? ((BigDecimal)colunas[0]).longValue() : null;
		this.situacao                 = (String) colunas[1];
		this.numeroDocumento          = (String) colunas[2];
		this.data                     = (colunas[3] != null)? ((Timestamp)colunas[3]).toLocalDateTime().toLocalDate() : null;
		this.valor                    = (colunas[4] != null)? ((BigDecimal)colunas[4]).doubleValue() : null;
		this.complemento              = (String) colunas[5];
		this.categoria                = (String) colunas[6];
		this.centroCusto              = (String) colunas[7];
		this.grupoContas              = (String) colunas[8];
		this.descricaoFornecedor      = (String) colunas[9];
		this.nomeFornecedor           = (String) colunas[10];
		this.semDocumento             = (colunas[11] != null)? ((String)colunas[11]).toUpperCase().equals("S") : null;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
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

	public String getDescricaoFornecedor() {
		return descricaoFornecedor;
	}

	public void setDescricaoFornecedor(String descricaoFornecedor) {
		this.descricaoFornecedor = descricaoFornecedor;
	}

	public String getNomeFornecedor() {
		return nomeFornecedor;
	}

	public void setNomeFornecedor(String nomeFornecedor) {
		this.nomeFornecedor = nomeFornecedor;
	}

	public Boolean getSemDocumento() {
		return semDocumento;
	}

	public void setSemDocumento(Boolean semDocumento) {
		this.semDocumento = semDocumento;
	}


}
