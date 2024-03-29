package br.com.crux.dao.dto;

import java.math.BigDecimal;

public class ComboFamiliarDTO {
	
	private Long id;
	private String nome;
	private String nomeFamiliarAndNomeAluno;
	
	
	public ComboFamiliarDTO() {
	}
	
	public ComboFamiliarDTO(Object[] colunas) {
		this.id                          = (colunas[0] != null)? ((BigDecimal)colunas[0]).longValue() : null;
		this.nome                        = (String) colunas[1];
		this.nomeFamiliarAndNomeAluno    = (String) colunas[2];
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

	public String getNomeFamiliarAndNomeAluno() {
		return nomeFamiliarAndNomeAluno;
	}

	public void setNomeFamiliarAndNomeAluno(String nomeFamiliarAndNomeAluno) {
		this.nomeFamiliarAndNomeAluno = nomeFamiliarAndNomeAluno;
	}
	
}
