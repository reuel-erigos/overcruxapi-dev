package br.com.crux.to;

public class MateriaisAcoesTO {

	private Long id;
	private Long idAcao;
	private MaterialTO material;
	private Long quantidadeMaterial;
	private Long usuarioAlteracao;

	public MateriaisAcoesTO() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public MaterialTO getMaterial() {
		return material;
	}

	public void setMaterial(MaterialTO material) {
		this.material = material;
	}

	public Long getQuantidadeMaterial() {
		return quantidadeMaterial;
	}

	public void setQuantidadeMaterial(Long quantidadeMaterial) {
		this.quantidadeMaterial = quantidadeMaterial;
	}

	public Long getUsuarioAlteracao() {
		return usuarioAlteracao;
	}

	public void setUsuarioAlteracao(Long usuarioAlteracao) {
		this.usuarioAlteracao = usuarioAlteracao;
	}

	public Long getIdAcao() {
		return idAcao;
	}

	public void setIdAcao(Long idAcao) {
		this.idAcao = idAcao;
	}


	

}
