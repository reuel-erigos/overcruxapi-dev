package br.com.crux.to;

public class ModuloTO {

	private Long id;
	private String descricao;
	private String nome;
	private ModuloTO moduloPai;
	private Long usuarioAlteracao;

	public ModuloTO() {
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

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public ModuloTO getModuloPai() {
		return moduloPai;
	}

	public void setModuloPai(ModuloTO moduloPai) {
		this.moduloPai = moduloPai;
	}

	public Long getUsuarioAlteracao() {
		return usuarioAlteracao;
	}

	public void setUsuarioAlteracao(Long usuarioAlteracao) {
		this.usuarioAlteracao = usuarioAlteracao;
	}

}
