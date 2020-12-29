package br.com.crux.dao.dto;

import java.math.BigDecimal;

public class EmpresaDTO {
	
	private Long id;
	private String codigo;
	private String nomeRazaoSocial;
	private String nomeFantasia;
	private String cnpj;
	
	public EmpresaDTO() {
	}
	
	public EmpresaDTO(Object[] colunas) {
		this.id              	= (colunas[0] != null)? ((BigDecimal)colunas[0]).longValue() : null;
		this.codigo    			= (String) colunas[1];
		this.nomeRazaoSocial 	= (String) colunas[2];
		this.nomeFantasia 		= (String) colunas[3];
		this.cnpj     			= (String) colunas[4];
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNomeRazaoSocial() {
		return nomeRazaoSocial;
	}

	public void setNomeRazaoSocial(String nomeRazaoSocial) {
		this.nomeRazaoSocial = nomeRazaoSocial;
	}

	public String getNomeFantasia() {
		return nomeFantasia;
	}

	public void setNomeFantasia(String nomeFantasia) {
		this.nomeFantasia = nomeFantasia;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	
	
	
}
