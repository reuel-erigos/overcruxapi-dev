package br.com.crux.to;

public class UsuariosUnidadesTO {

	private Long id;
	private UnidadeTO unidade;
	private UsuariosSistemaTO usuarioSistema;
	private Long usuarioAlteracao;

	public UsuariosUnidadesTO() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public UnidadeTO getUnidade() {
		return unidade;
	}

	public void setUnidade(UnidadeTO unidade) {
		this.unidade = unidade;
	}

	public UsuariosSistemaTO getUsuarioSistema() {
		return usuarioSistema;
	}

	public void setUsuarioSistema(UsuariosSistemaTO usuarioSistema) {
		this.usuarioSistema = usuarioSistema;
	}

	public Long getUsuarioAlteracao() {
		return usuarioAlteracao;
	}

	public void setUsuarioAlteracao(Long usuarioAlteracao) {
		this.usuarioAlteracao = usuarioAlteracao;
	}

}
