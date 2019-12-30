package br.com.crux.dao.dto;

import java.math.BigDecimal;

public class PerfilAcessoUsuarioDTO {
	
	private Long idUsuario;
	private String nomeUsuario;
	private Long idUnidade;
	private String nomeUnidade;
	private Long idModulo;
	private String nomeModulo;
	private Long idGrupoModulo;
	private String nomeGrupoModulo;
	private String descricaoGrupoModulo;
	private Long idUsuarioGrupo;
	
	public PerfilAcessoUsuarioDTO() {
	}
	
	public PerfilAcessoUsuarioDTO(Object[] colunas) {
		this.idUsuario            = (colunas[0] != null)? ((BigDecimal)colunas[0]).longValue() : null;
		this.nomeUsuario          = (String) colunas[1];
		this.idUnidade            = (colunas[2] != null)? ((BigDecimal)colunas[2]).longValue() : null;
		this.nomeUnidade          = (String) colunas[3];
		this.idModulo             = (colunas[4] != null)? ((BigDecimal)colunas[4]).longValue() : null;
		this.nomeModulo           = (String) colunas[5];
		this.idGrupoModulo        = (colunas[6] != null)? ((BigDecimal)colunas[6]).longValue() : null;
		this.nomeGrupoModulo      = (String) colunas[7];
		this.descricaoGrupoModulo = (String) colunas[8];
		this.idUsuarioGrupo        = (colunas[9] != null)? ((BigDecimal)colunas[9]).longValue() : null;
	}

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public Long getIdUnidade() {
		return idUnidade;
	}

	public void setIdUnidade(Long idUnidade) {
		this.idUnidade = idUnidade;
	}

	public String getNomeUnidade() {
		return nomeUnidade;
	}

	public void setNomeUnidade(String nomeUnidade) {
		this.nomeUnidade = nomeUnidade;
	}

	public Long getIdModulo() {
		return idModulo;
	}

	public void setIdModulo(Long idModulo) {
		this.idModulo = idModulo;
	}

	public String getNomeModulo() {
		return nomeModulo;
	}

	public void setNomeModulo(String nomeModulo) {
		this.nomeModulo = nomeModulo;
	}

	public Long getIdGrupoModulo() {
		return idGrupoModulo;
	}

	public void setIdGrupoModulo(Long idGrupoModulo) {
		this.idGrupoModulo = idGrupoModulo;
	}

	public String getNomeGrupoModulo() {
		return nomeGrupoModulo;
	}

	public void setNomeGrupoModulo(String nomeGrupoModulo) {
		this.nomeGrupoModulo = nomeGrupoModulo;
	}

	public String getDescricaoGrupoModulo() {
		return descricaoGrupoModulo;
	}

	public void setDescricaoGrupoModulo(String descricaoGrupoModulo) {
		this.descricaoGrupoModulo = descricaoGrupoModulo;
	}

	public Long getIdUsuarioGrupo() {
		return idUsuarioGrupo;
	}

	public void setIdUsuarioGrupo(Long idUsuarioGrupo) {
		this.idUsuarioGrupo = idUsuarioGrupo;
	}

	


}
