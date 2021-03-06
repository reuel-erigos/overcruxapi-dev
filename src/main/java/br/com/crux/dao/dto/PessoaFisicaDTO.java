package br.com.crux.dao.dto;

import java.math.BigDecimal;

public class PessoaFisicaDTO {
	
	private Long id;
	private String nome;
	private String nomeMae;
	private String nomePai;
	private String cpf;
	
	public PessoaFisicaDTO() {
	}
	
	public PessoaFisicaDTO(Object[] colunas) {
		this.id      = (colunas[0] != null)? ((BigDecimal)colunas[0]).longValue() : null;
		this.nome    = (String) colunas[1];
		this.nomeMae = (String) colunas[2];
		this.nomePai = (String) colunas[3];
		this.cpf     = (String) colunas[4];
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

	public String getNomeMae() {
		return nomeMae;
	}

	public void setNomeMae(String nomeMae) {
		this.nomeMae = nomeMae;
	}

	public String getNomePai() {
		return nomePai;
	}

	public void setNomePai(String nomePai) {
		this.nomePai = nomePai;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	
}
