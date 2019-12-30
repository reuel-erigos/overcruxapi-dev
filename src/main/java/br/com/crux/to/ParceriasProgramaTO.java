package br.com.crux.to;

import java.time.LocalDateTime;
import java.util.List;

public class ParceriasProgramaTO {

	private Long id;
	private String dsTipoParceria;
	private LocalDateTime dtFimParceria;
	private LocalDateTime dtInicioParceria;
	private EmpresaTO empresa;
	private ProgramaTO programa;
	private Double valorParceria;
	private List<MateriaisProgramaTO> materiaisPrograma;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDsTipoParceria() {
		return dsTipoParceria;
	}
	public void setDsTipoParceria(String dsTipoParceria) {
		this.dsTipoParceria = dsTipoParceria;
	}
	public LocalDateTime getDtFimParceria() {
		return dtFimParceria;
	}
	public void setDtFimParceria(LocalDateTime dtFimParceria) {
		this.dtFimParceria = dtFimParceria;
	}
	public LocalDateTime getDtInicioParceria() {
		return dtInicioParceria;
	}
	public void setDtInicioParceria(LocalDateTime dtInicioParceria) {
		this.dtInicioParceria = dtInicioParceria;
	}
	public EmpresaTO getEmpresa() {
		return empresa;
	}
	public void setEmpresa(EmpresaTO empresa) {
		this.empresa = empresa;
	}
	public ProgramaTO getPrograma() {
		return programa;
	}
	public void setPrograma(ProgramaTO programa) {
		this.programa = programa;
	}
	public Double getValorParceria() {
		return valorParceria;
	}
	public void setValorParceria(Double valorParceria) {
		this.valorParceria = valorParceria;
	}
	public List<MateriaisProgramaTO> getMateriaisPrograma() {
		return materiaisPrograma;
	}
	public void setMateriaisPrograma(List<MateriaisProgramaTO> materiaisPrograma) {
		this.materiaisPrograma = materiaisPrograma;
	}
	
	

}
