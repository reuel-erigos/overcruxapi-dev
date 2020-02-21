package br.com.crux.to;

public class ParceriasCategoriasTO {

	private Long id;
	private ParceriasProgramaTO parceriasPrograma;
	private ParceriasProjetoTO parceriasProjeto;
	private CategoriasContabeisTO categoriasContabeis;
	private Long valorParceriaCategoria;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public ParceriasProgramaTO getParceriasPrograma() {
		return parceriasPrograma;
	}
	public void setParceriasPrograma(ParceriasProgramaTO parceriasPrograma) {
		this.parceriasPrograma = parceriasPrograma;
	}
	public ParceriasProjetoTO getParceriasProjeto() {
		return parceriasProjeto;
	}
	public void setParceriasProjeto(ParceriasProjetoTO parceriasProjeto) {
		this.parceriasProjeto = parceriasProjeto;
	}
	public CategoriasContabeisTO getCategoriasContabeis() {
		return categoriasContabeis;
	}
	public void setCategoriasContabeis(CategoriasContabeisTO categoriasContabeis) {
		this.categoriasContabeis = categoriasContabeis;
	}
	public Long getValorParceriaCategoria() {
		return valorParceriaCategoria;
	}
	public void setValorParceriaCategoria(Long valorParceriaCategoria) {
		this.valorParceriaCategoria = valorParceriaCategoria;
	}
	
}
