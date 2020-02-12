
package br.com.crux.to;

import java.time.LocalDateTime;

public class SaldosContasBancariaTO {

	private Long id;
	private String descricaoSaldo;
	private LocalDateTime dataAtualizacaoSaldo;
	private LocalDateTime dataSaldo;
	private Double valorSaldo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricaoSaldo() {
		return descricaoSaldo;
	}

	public void setDescricaoSaldo(String descricaoSaldo) {
		this.descricaoSaldo = descricaoSaldo;
	}

	public LocalDateTime getDataAtualizacaoSaldo() {
		return dataAtualizacaoSaldo;
	}

	public void setDataAtualizacaoSaldo(LocalDateTime dataAtualizacaoSaldo) {
		this.dataAtualizacaoSaldo = dataAtualizacaoSaldo;
	}

	public LocalDateTime getDataSaldo() {
		return dataSaldo;
	}

	public void setDataSaldo(LocalDateTime dataSaldo) {
		this.dataSaldo = dataSaldo;
	}

	public Double getValorSaldo() {
		return valorSaldo;
	}

	public void setValorSaldo(Double valorSaldo) {
		this.valorSaldo = valorSaldo;
	}

}