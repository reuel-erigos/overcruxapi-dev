package br.com.crux.dao.dto;

import java.math.BigDecimal;

public class ColaboradoresGestaoPessoalDTO {
	
	private Long idPessoaFisica;
	private Long idColaborador;
	private String nomeCobaborador;
	private String email;
	private String unidade;
	private String departamento;
	private String cargo;
	private String funcao;
	
	
	public ColaboradoresGestaoPessoalDTO() {
	}
	
	public ColaboradoresGestaoPessoalDTO(Object[] colunas) {
		this.idPessoaFisica      = (colunas[0] != null)? ((BigDecimal)colunas[0]).longValue() : null;
		this.idColaborador       = (colunas[1] != null)? ((BigDecimal)colunas[1]).longValue() : null;
		this.nomeCobaborador     = (colunas[2] != null) ? (String) colunas[2] : ""; 
		this.email               = (colunas[3] != null) ? (String) colunas[3] : ""; 
		this.unidade             = (colunas[4] != null) ? (String) colunas[4] : ""; 
		this.departamento        = (colunas[5] != null) ? (String) colunas[5] : ""; 
		this.cargo               = (colunas[6] != null) ? (String) colunas[6] : ""; 
		this.funcao              = (colunas[7] != null) ? (String) colunas[7] : ""; 
	}

	public Long getIdPessoaFisica() {
		return idPessoaFisica;
	}

	public void setIdPessoaFisica(Long idPessoaFisica) {
		this.idPessoaFisica = idPessoaFisica;
	}

	public Long getIdColaborador() {
		return idColaborador;
	}

	public void setIdColaborador(Long idColaborador) {
		this.idColaborador = idColaborador;
	}

	public String getNomeCobaborador() {
		return nomeCobaborador;
	}

	public void setNomeCobaborador(String nomeCobaborador) {
		this.nomeCobaborador = nomeCobaborador;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUnidade() {
		return unidade;
	}

	public void setUnidade(String unidade) {
		this.unidade = unidade;
	}

	public String getDepartamento() {
		return departamento;
	}

	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public String getFuncao() {
		return funcao;
	}

	public void setFuncao(String funcao) {
		this.funcao = funcao;
	}


	
}
