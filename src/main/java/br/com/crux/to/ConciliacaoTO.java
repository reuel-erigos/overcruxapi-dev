package br.com.crux.to;

import java.time.LocalDate;

public class ConciliacaoTO {

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
	private Boolean semDocumento;

	public ConciliacaoTO() {
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

	public void setData(LocalDate dataPagamentoRecebimento) {
		this.data = dataPagamentoRecebimento;
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

	public void setCategoria(String apropriacaoFinanceira) {
		this.categoria = apropriacaoFinanceira;
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

	public void setComplemento(String complementoHistorico) {
		this.complemento = complementoHistorico;
	}

	public String getCentroCusto() {
		return centroCusto;
	}

	public void setCentroCusto(String nomeProjetoPrograma) {
		this.centroCusto = nomeProjetoPrograma;
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

	public Boolean getSemDocumento() {
		return semDocumento;
	}

	public void setSemDocumento(Boolean semDocumento) {
		this.semDocumento = semDocumento;
	}

	
	
}
