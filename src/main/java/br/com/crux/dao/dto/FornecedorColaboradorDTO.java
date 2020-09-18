package br.com.crux.dao.dto;

import java.math.BigDecimal;

public class FornecedorColaboradorDTO {

	private Long   id;
	private String tipo;
	private String cpf;
	private String nome;
	private Long   idInstituicao;
	
	public FornecedorColaboradorDTO() {
	}
	
	public FornecedorColaboradorDTO(Object[] colunas) {
		this.tipo          = (String) colunas[0];
		this.id            = (colunas[1] != null)?((BigDecimal)colunas[1]).longValue() : null;
		this.nome          = (String) colunas[2];
		this.cpf           = (String) colunas[3];
		this.idInstituicao = (colunas[4] != null)?((BigDecimal)colunas[4]).longValue() : null;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Long getIdInstituicao() {
		return idInstituicao;
	}

	public void setIdInstituicao(Long idInstituicao) {
		this.idInstituicao = idInstituicao;
	}
	
}
