package br.com.crux.to;

import java.util.List;

public class ParceriasCategoriasTO {

	private Long id;
	private ParceriasProgramaTO parceriasPrograma;
	private ParceriasProjetoTO parceriasProjeto;
	private CategoriasContabeisTO categoriasContabeis;
	private Double valorParceriaCategoria;

	private List<AditivoParceriaCategoriaTO> aditivosParceriasCategorias;

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

	public Double getValorParceriaCategoria() {
		return valorParceriaCategoria;
	}

	public void setValorParceriaCategoria(Double valorParceriaCategoria) {
		this.valorParceriaCategoria = valorParceriaCategoria;
	}

	public List<AditivoParceriaCategoriaTO> getAditivosParceriasCategorias() {
		return aditivosParceriasCategorias;
	}

	public void setAditivosParceriasCategorias(List<AditivoParceriaCategoriaTO> aditivosParceriasCategorias) {
		this.aditivosParceriasCategorias = aditivosParceriasCategorias;
	}

}
