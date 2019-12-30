package br.com.crux.to;

import java.time.LocalDateTime;

public class MateriaisProgramaTO {

	private Long id;
	private LocalDateTime dataFim;
	private LocalDateTime dataInicio;
	private MaterialTO material;
	private ProgramaTO programa;
	private Double vlMaterial;
	private ParceriasProgramaTO parceriasPrograma;
	
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
	public ProgramaTO getPrograma() {
		return programa;
	}
	public void setPrograma(ProgramaTO programa) {
		this.programa = programa;
	}
	public Double getVlMaterial() {
		return vlMaterial;
	}
	public void setVlMaterial(Double vlMaterial) {
		this.vlMaterial = vlMaterial;
	}
	public ParceriasProgramaTO getParceriasPrograma() {
		return parceriasPrograma;
	}
	public void setParceriasPrograma(ParceriasProgramaTO parceriasPrograma) {
		this.parceriasPrograma = parceriasPrograma;
	}

	
	
}
