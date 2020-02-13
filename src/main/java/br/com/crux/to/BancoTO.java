
package br.com.crux.to;

public class BancoTO {

	private Long id;
	private String nome;
	private String numero;

	public BancoTO(String nome, String numero) {
		super();
		this.nome = nome;
		this.numero = numero;
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

	public void setNome(String nomeBanco) {
		this.nome = nomeBanco;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numeroBanco) {
		this.numero = numeroBanco;
	}

}