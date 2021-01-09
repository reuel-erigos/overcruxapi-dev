package br.com.crux.to.relatorios.financeiro;

public class NormativaPagamentosTO {
	
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
	
	public NormativaPagamentosTO() {
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
