package br.com.crux.to;

import java.time.LocalDateTime;

public class AditivoParceriaProgramaTO {

	private Long id;
	private Long idParceriasPrograma;
	private LocalDateTime dataAditivo;
	private Double valorAditivo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdParceriasPrograma() {
		return idParceriasPrograma;
	}

	public void setIdParceriasPrograma(Long idParceriasPrograma) {
		this.idParceriasPrograma = idParceriasPrograma;
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
