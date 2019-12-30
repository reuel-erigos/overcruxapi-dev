package br.com.crux.to;

public class ComposicaoRhProgramaTO {

	private Long id;
	private CargoTO cargo;
	private Long qtd;
	private TiposContratacoesTO tiposContratacoes;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getQtd() {
		return qtd;
	}

	public void setQtd(Long qtd) {
		this.qtd = qtd;
	}

	public TiposContratacoesTO getTiposContratacoes() {
		return tiposContratacoes;
	}

	public void setTiposContratacoes(TiposContratacoesTO tiposContratacoes) {
		this.tiposContratacoes = tiposContratacoes;
	}

	public CargoTO getCargo() {
		return cargo;
	}

	public void setCargo(CargoTO cargo) {
		this.cargo = cargo;
	}

}
