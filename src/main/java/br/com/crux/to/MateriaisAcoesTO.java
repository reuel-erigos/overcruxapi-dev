package br.com.crux.to;

public class MateriaisAcoesTO {

	private Long id;
	private AcaoTO acao;
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

	public AcaoTO getAcao() {
		return acao;
	}

	public void setAcao(AcaoTO acao) {
		this.acao = acao;
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

}
