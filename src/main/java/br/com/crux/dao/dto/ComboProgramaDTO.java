package br.com.crux.dao.dto;

import java.math.BigDecimal;

public class ComboProgramaDTO {
	
	private Long id;
	private String nome;
	
	public ComboProgramaDTO() {
	}
	
	public ComboProgramaDTO(Object[] colunas) {
		this.id      = (colunas[0] != null)? ((BigDecimal)colunas[0]).longValue() : null;
		this.nome    = (String) colunas[1];
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

	
}
