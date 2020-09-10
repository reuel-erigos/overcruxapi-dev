package br.com.crux.to;

public class RateiosPagamentosTO{

	private Long id;
	private Long idPagamentoFatura;
	private ContasBancariaTO contaBancaria;
	private Boolean statusPercentual;
	private Double valorRateio;
	private Long usuarioAlteracao;
	
	public RateiosPagamentosTO() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdPagamentoFatura() {
		return idPagamentoFatura;
	}

	public void setIdPagamentoFatura(Long idPagamentoFatura) {
		this.idPagamentoFatura = idPagamentoFatura;
	}

	public ContasBancariaTO getContaBancaria() {
		return contaBancaria;
	}

	public void setContaBancaria(ContasBancariaTO contaBancaria) {
		this.contaBancaria = contaBancaria;
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