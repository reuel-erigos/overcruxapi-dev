package br.com.crux.to;

import java.io.Serializable;


public class AnexosAcaoPlanejamentoTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private ArquivoMetadadoTO metadados;
	private Long idAcao;

	
	public AnexosAcaoPlanejamentoTO() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ArquivoMetadadoTO getMetadados() {
		return metadados;
	}

	public void setMetadados(ArquivoMetadadoTO metadados) {
		this.metadados = metadados;
	}

	public Long getIdAcao() {
		return idAcao;
	}

	public void setIdAcao(Long idAcao) {
		this.idAcao = idAcao;
	}

}