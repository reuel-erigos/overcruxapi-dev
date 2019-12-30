package br.com.crux.dao.dto;

import java.math.BigDecimal;

public class UsuarioUnidadeDTO {

	
	private Long idPessoaFisica;
	private Long idUsuario;
	private String nomeCompleto;
	private String email;
	
	public UsuarioUnidadeDTO() {
	}
	
	public UsuarioUnidadeDTO(Object[] colunas) {
		this.idPessoaFisica     = (colunas[0] != null)? ((BigDecimal)colunas[0]).longValue() : null;
		this.idUsuario          = (colunas[1] != null)? ((BigDecimal)colunas[1]).longValue() : null;
		this.nomeCompleto       = (String) colunas[2];
		this.email              = (String) colunas[3];
	}

	public Long getIdPessoaFisica() {
		return idPessoaFisica;
	}

	public void setIdPessoaFisica(Long idPessoaFisica) {
		this.idPessoaFisica = idPessoaFisica;
	}

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getNomeCompleto() {
		return nomeCompleto;
	}

	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
