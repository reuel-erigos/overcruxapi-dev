package br.com.crux.to;

public class CategoriasContabeisTO {

	private Long id;
	private CategoriasContabeisTO categoriaSuperior;
	private String tipo;
	private String nome;
	private String descricaoCategoria;
	private String codigoCategoriaContabil;
	private Long idInstituicao;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public CategoriasContabeisTO getCategoriaSuperior() {
		return categoriaSuperior;
	}

	public void setCategoriaSuperior(CategoriasContabeisTO categoriaSuperior) {
		this.categoriaSuperior = categoriaSuperior;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricaoCategoria() {
		return descricaoCategoria;
	}

	public void setDescricaoCategoria(String descricaoCategoria) {
		this.descricaoCategoria = descricaoCategoria;
	}

	public String getCodigoCategoriaContabil() {
		return codigoCategoriaContabil;
	}

	public void setCodigoCategoriaContabil(String codigoCategoriaContabil) {
		this.codigoCategoriaContabil = codigoCategoriaContabil;
	}

	public Long getIdInstituicao() {
		return idInstituicao;
	}

	public void setIdInstituicao(Long idInstituicao) {
		this.idInstituicao = idInstituicao;
	}
	
}
