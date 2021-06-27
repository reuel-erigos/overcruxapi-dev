package br.com.crux.to;

public class RateiosCategoriasMovimentosTO {

	private Long id;
	private Long idCategoriaMovimento;
	private ProgramaTO programa;
	private ProjetoTO projeto;
	private Double valorRateio;
	private Long usuarioAlteracao;

	public RateiosCategoriasMovimentosTO() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdCategoriaMovimento() {
		return idCategoriaMovimento;
	}

	public void setIdCategoriaMovimento(Long idCategoriaMovimento) {
		this.idCategoriaMovimento = idCategoriaMovimento;
	}

	public ProgramaTO getPrograma() {
		return programa;
	}

	public void setPrograma(ProgramaTO programa) {
		this.programa = programa;
	}

	public ProjetoTO getProjeto() {
		return projeto;
	}

	public void setProjeto(ProjetoTO projeto) {
		this.projeto = projeto;
	}

	public Double getValorRateio() {
		return valorRateio;
	}

	public void setValorRateio(Double valorRateio) {
		this.valorRateio = valorRateio;
	}

	public Long getUsuarioAlteracao() {
		return usuarioAlteracao;
	}

	public void setUsuarioAlteracao(Long usuarioAlteracao) {
		this.usuarioAlteracao = usuarioAlteracao;
	}


}
