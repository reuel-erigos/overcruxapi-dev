package br.com.crux.to;

import java.time.LocalDateTime;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import br.com.crux.infra.adapter.LocalDateTimeAdapter;


public class ReembolsosPagamentosTO {

	private Long id;
	private Long idPagamentoFatura;
	private ContasBancariaTO contaBancaria;
	private String descricao;
	
	@XmlJavaTypeAdapter(LocalDateTimeAdapter.class) 
	private LocalDateTime data;
	
	private Boolean statusPercentual;
	private Double valor;
	private Long usuarioAlteracao;
	
	private ContasBancariaTO contaBancariaDestino;

	
	public ReembolsosPagamentosTO() {
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

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public LocalDateTime getData() {
		return data;
	}

	public void setData(LocalDateTime data) {
		this.data = data;
	}

	public Boolean getStatusPercentual() {
		return statusPercentual;
	}

	public void setStatusPercentual(Boolean statusPercentual) {
		this.statusPercentual = statusPercentual;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valorRateio) {
		this.valor = valorRateio;
	}

	public Long getUsuarioAlteracao() {
		return usuarioAlteracao;
	}

	public void setUsuarioAlteracao(Long usuarioAlteracao) {
		this.usuarioAlteracao = usuarioAlteracao;
	}

	public ContasBancariaTO getContaBancariaDestino() {
		return contaBancariaDestino;
	}

	public void setContaBancariaDestino(ContasBancariaTO contaBancariaDestino) {
		this.contaBancariaDestino = contaBancariaDestino;
	}
	
}