package br.com.crux.to;

import java.math.BigDecimal;

public class ComposicaoRhProjetoTO {

	private Long id;
	private CargoTO cargo;
	private BigDecimal qtd;
	private TiposContratacoesTO tiposContratacoes;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public CargoTO getCargo() {
		return cargo;
	}

	public void setCargo(CargoTO cargo) {
		this.cargo = cargo;
	}

	public BigDecimal getQtd() {
		return qtd;
	}

	public void setQtd(BigDecimal qtd) {
		this.qtd = qtd;
	}

	public TiposContratacoesTO getTiposContratacoes() {
		return tiposContratacoes;
	}

	public void setTiposContratacoes(TiposContratacoesTO tiposContratacoes) {
		this.tiposContratacoes = tiposContratacoes;
	}

}
