package br.com.crux.dao.dto;

import java.math.BigDecimal;

public class ModuloDTO {

	private Long idModulo;
	private String descricao;
	private String nome;
	
	public ModuloDTO() {
	}
	
	public ModuloDTO(Object[] colunas) {
		this.idModulo      = (colunas[0] != null)? ((BigDecimal)colunas[0]).longValue() : null;
		this.descricao       = (String) colunas[1];
		this.nome        = (String) colunas[2];
	}

	public Long getIdModulo() {
		return idModulo;
	}

	public void setIdModulo(Long idModulo) {
		this.idModulo = idModulo;
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

	
}
