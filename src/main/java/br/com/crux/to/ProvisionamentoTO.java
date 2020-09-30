package br.com.crux.to;

import java.time.LocalDate;

public class ProvisionamentoTO {

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

	public ProvisionamentoTO() {
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
