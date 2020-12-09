package br.com.crux.to;

public class TiposAtividadesTO {
	private Long id;
	private String descricao;
	private InstituicaoTO instituicao;
	
	
	public TiposAtividadesTO() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public InstituicaoTO getInstituicao() {
		return instituicao;
	}

	public void setInstituicao(InstituicaoTO instituicao) {
		this.instituicao = instituicao;
	}



}