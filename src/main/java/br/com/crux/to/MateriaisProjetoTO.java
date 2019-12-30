package br.com.crux.to;

import java.time.LocalDateTime;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import br.com.crux.infra.adapter.LocalDateTimeAdapter;

public class MateriaisProjetoTO {

	private Long id;

	@XmlJavaTypeAdapter(LocalDateTimeAdapter.class) private LocalDateTime dataFim;

	@XmlJavaTypeAdapter(LocalDateTimeAdapter.class) private LocalDateTime dataInicio;

	private MaterialTO material;

	private Double valorMaterial;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getDataFim() {
		return dataFim;
	}

	public void setDataFim(LocalDateTime dataFim) {
		this.dataFim = dataFim;
	}

	public LocalDateTime getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(LocalDateTime dataInicio) {
		this.dataInicio = dataInicio;
	}

	public MaterialTO getMaterial() {
		return material;
	}

	public void setMaterial(MaterialTO material) {
		this.material = material;
	}

	public Double getValorMaterial() {
		return valorMaterial;
	}

	public void setValorMaterial(Double valorMaterial) {
		this.valorMaterial = valorMaterial;
	}

}
