package br.com.crux.to;

public class TributosItensMovimentacoesTO {

	private Long id;
	private Long idItemMovimentacao;
	private TributosTO tributo;
	private Long usuarioAlteracao;

	
	public TributosItensMovimentacoesTO() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdItemMovimentacao() {
		return idItemMovimentacao;
	}

	public void setIdItemMovimentacao(Long idItemMovimentacao) {
		this.idItemMovimentacao = idItemMovimentacao;
	}

	public TributosTO getTributo() {
		return tributo;
	}

	public void setTributo(TributosTO tributo) {
		this.tributo = tributo;
	}

	public Long getUsuarioAlteracao() {
		return usuarioAlteracao;
	}

	public void setUsuarioAlteracao(Long usuarioAlteracao) {
		this.usuarioAlteracao = usuarioAlteracao;
	}
	
}