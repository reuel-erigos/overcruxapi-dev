package br.com.crux.to;

public class RateiosMovimentacoesUnidadesTO {

	private Long id;
	private Long idMovimentacao;
	private Long idUnidade;
	private Boolean statusPercentual;
	private Double valorRateio;
	private Long usuarioAlteracao;

	
	public RateiosMovimentacoesUnidadesTO() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdMovimentacao() {
		return idMovimentacao;
	}

	public void setIdMovimentacao(Long idMovimentacao) {
		this.idMovimentacao = idMovimentacao;
	}

	public Long getIdUnidade() {
		return idUnidade;
	}

	public void setIdUnidade(Long idUnidade) {
		this.idUnidade = idUnidade;
	}

	public Boolean getStatusPercentual() {
		return statusPercentual;
	}

	public void setStatusPercentual(Boolean statusPercentual) {
		this.statusPercentual = statusPercentual;
	}

	public Double getValorRateio() {
		return valorRateio;
	}

	public void setValorRateio(Double valorRateio) {
		this.valorRateio = valorRateio;
	}

	public Long getUsuarioAlteracao() {
		return usuarioAlteracao;
	}

	public void setUsuarioAlteracao(Long usuarioAlteracao) {
		this.usuarioAlteracao = usuarioAlteracao;
	}
	
}