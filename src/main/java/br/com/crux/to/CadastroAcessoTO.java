package br.com.crux.to;

public class CadastroAcessoTO {
	
	private Long   idUsuario;
	private Long   idInstituicao;
	private Long   idModulo;
	private Long   idGrupoModulo;
	
	
	public Long getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}
	public Long getIdInstituicao() {
		return idInstituicao;
	}
	public void setIdInstituicao(Long codigoUnidade) {
		this.idInstituicao = codigoUnidade;
	}
	public Long getIdModulo() {
		return idModulo;
	}
	public void setIdModulo(Long nomeUnidade) {
		this.idModulo = nomeUnidade;
	}
	public Long getIdGrupoModulo() {
		return idGrupoModulo;
	}
	public void setIdGrupoModulo(Long idGrupoModulo) {
		this.idGrupoModulo = idGrupoModulo;
	}

	

}
