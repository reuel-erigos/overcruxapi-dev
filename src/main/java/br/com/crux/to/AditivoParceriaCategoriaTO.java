package br.com.crux.to;

import java.time.LocalDateTime;

public class AditivoParceriaCategoriaTO {

	private Long id;
	private Long idParceriasCategorias;
	private LocalDateTime dataAditivo;
	private Double valorAditivo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdParceriasCategorias() {
		return idParceriasCategorias;
	}

	public void setIdParceriasCategorias(Long idParceriasCategorias) {
		this.idParceriasCategorias = idParceriasCategorias;
	}

	public LocalDateTime getDataAditivo() {
		return dataAditivo;
	}

	public void setDataAditivo(LocalDateTime dataAditivo) {
		this.dataAditivo = dataAditivo;
	}

	public Double getValorAditivo() {
		return valorAditivo;
	}

	public void setValorAditivo(Double valorAditivo) {
		this.valorAditivo = valorAditivo;
	}

}
