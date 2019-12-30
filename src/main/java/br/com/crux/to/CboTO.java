package br.com.crux.to;

public class CboTO {

	private Long id;
	private String numero;
	private String nomeTitulo;
	private String descricaoTipo;
	private Long usuarioAlteracao;

	public CboTO() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getNomeTitulo() {
		return nomeTitulo;
	}

	public void setNomeTitulo(String nomeTitulo) {
		this.nomeTitulo = nomeTitulo;
	}

	public String getDescricaoTipo() {
		return descricaoTipo;
	}

	public void setDescricaoTipo(String tipo) {
		this.descricaoTipo = tipo;
	}

	public Long getUsuarioAlteracao() {
		return usuarioAlteracao;
	}

	public void setUsuarioAlteracao(Long usuarioAlteracao) {
		this.usuarioAlteracao = usuarioAlteracao;
	}



}