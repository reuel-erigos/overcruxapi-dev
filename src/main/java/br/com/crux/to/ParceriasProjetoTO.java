package br.com.crux.to;

import java.time.LocalDateTime;
import java.util.List;

public class ParceriasProjetoTO {

	private Long id;
	private String descricaoTipoParceria;
	private LocalDateTime dataFim;
	private LocalDateTime dataInicio;
	private EmpresaTO empresa;
	private ProjetoTO projeto;
	private Long quantidadeMaterial;
	private Double valorParceria;
	
	private List<MateriaisProjetoTO> materiaisProjeto;
	private List<ParceriasCategoriasTO> parceriasCategorias;
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricaoTipoParceria() {
		return descricaoTipoParceria;
	}

	public void setDescricaoTipoParceria(String descricaoTipoParceria) {
		this.descricaoTipoParceria = descricaoTipoParceria;
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

	public Double getValorParceria() {
		return valorParceria;
	}

	public void setValorParceria(Double valorParceria) {
		this.valorParceria = valorParceria;
	}

	public EmpresaTO getEmpresa() {
		return empresa;
	}

	public void setEmpresa(EmpresaTO empresa) {
		this.empresa = empresa;
	}

	public ProjetoTO getProjeto() {
		return projeto;
	}

	public void setProjeto(ProjetoTO projeto) {
		this.projeto = projeto;
	}

	public List<MateriaisProjetoTO> getMateriaisProjeto() {
		return materiaisProjeto;
	}

	public void setMateriaisProjeto(List<MateriaisProjetoTO> materiaisProjeto) {
		this.materiaisProjeto = materiaisProjeto;
	}

	public Long getQuantidadeMaterial() {
		return quantidadeMaterial;
	}

	public void setQuantidadeMaterial(Long quantidadeMaterial) {
		this.quantidadeMaterial = quantidadeMaterial;
	}

	public List<ParceriasCategoriasTO> getParceriasCategorias() {
		return parceriasCategorias;
	}

	public void setParceriasCategorias(List<ParceriasCategoriasTO> parceriasCategorias) {
		this.parceriasCategorias = parceriasCategorias;
	}

	
}
