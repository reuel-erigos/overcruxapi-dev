package br.com.crux.to;

import java.io.Serializable;

public class GrupoAcoesSimlesTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String numeroGrupo;	
	private OficinasTO atividade;
	private String descricao;
	private String statusAnalise;
	private Boolean statusEnvioAnalise;
	
	public GrupoAcoesSimlesTO() {
	}

	public GrupoAcoesSimlesTO(Long id) {
		this.id = id;
	}	

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumeroGrupo() {
		return numeroGrupo;
	}

	public void setNumeroGrupo(String numeroGrupo) {
		this.numeroGrupo = numeroGrupo;
	}

	public OficinasTO getAtividade() {
		return atividade;
	}

	public void setAtividade(OficinasTO atividade) {
		this.atividade = atividade;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getStatusAnalise() {
		return statusAnalise;
	}

	public void setStatusAnalise(String statusAnalise) {
		this.statusAnalise = statusAnalise;
	}

	public Boolean getStatusEnvioAnalise() {
		return statusEnvioAnalise;
	}

	public void setStatusEnvioAnalise(Boolean statusEnvioAnalise) {
		this.statusEnvioAnalise = statusEnvioAnalise;
	}

}
