package br.com.crux.to;

import java.time.LocalDateTime;

public class AditivoParceriaProjetoTO {

	private Long id;
	private Long idParceriasProjeto;
	private LocalDateTime dataAditivo;
	private Double valorAditivo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Long getIdParceriasProjeto() {
		return idParceriasProjeto;
	}

	public void setIdParceriasProjeto(Long idParceriasProjeto) {
		this.idParceriasProjeto = idParceriasProjeto;
	}

}
