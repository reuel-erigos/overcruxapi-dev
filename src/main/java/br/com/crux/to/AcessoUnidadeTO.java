package br.com.crux.to;

public class AcessoUnidadeTO {
	
	private Long id;
	private String identificador;
	private String nome;
	
	
	public AcessoUnidadeTO() {
	}
	
	public AcessoUnidadeTO(String identificador,Long id, String nome) {
		super();
		this.id = id;
		this.identificador = identificador;
		this.nome = nome;
	}
	
	
	public String getIdentificador() {
		return identificador;
	}

	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public String toString() {
		return "AcessoUnidadeTO [id=" + id + ", identificador=" + identificador + ", nome=" + nome + "]";
	}

	
}
