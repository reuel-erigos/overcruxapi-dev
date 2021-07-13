package br.com.crux.to;

public class PlanosContasTO {

	private Long id;
	private String planoConta;
	private String codigoCategoriaContabil;
	private Long idInstituicao;
	private Boolean sintetica;
	
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long idCategoria) {
		this.id = idCategoria;
	}
	
	public String getPlanoConta() {
		return planoConta;
	}
	
	public void setPlanoConta(String planoConta) {
		this.planoConta = planoConta;
	}
	
	public String getCodigoCategoriaContabil() {
		return codigoCategoriaContabil;
	}
	
	public void setCodigoCategoriaContabil(String categoriaContabil) {
		this.codigoCategoriaContabil = categoriaContabil;
	}
	
	public Long getIdInstituicao() {
		return idInstituicao;
	}
	
	public void setIdInstituicao(Long idInstituicao) {
		this.idInstituicao = idInstituicao;
	}

	public Boolean getSintetica() {
		return sintetica;
	}

	public void setSintetica(Boolean sintetica) {
		this.sintetica = sintetica;
	}
	
	
}
