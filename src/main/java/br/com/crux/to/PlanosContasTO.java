package br.com.crux.to;

public class PlanosContasTO {

	private Long idCategoria;
	private String planoConta;
	private String codigoCategoriaContabil;
	private Long idInstituicao;
	
	public Long getIdCategoria() {
		return idCategoria;
	}
	
	public void setIdCategoria(Long idCategoria) {
		this.idCategoria = idCategoria;
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
	
}
