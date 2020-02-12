
package br.com.crux.to;

import java.math.BigDecimal;

public class BancoTO {

	private Long id;
	private String nome;
	private BigDecimal numero;
	
	
	
	public BancoTO(String nome, BigDecimal numero) {
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
	public BigDecimal getNumero() {
		return numero;
	}
	public void setNumero(BigDecimal numeroBanco) {
		this.numero = numeroBanco;
	}
	
	

}