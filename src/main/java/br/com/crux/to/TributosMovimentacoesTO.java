package br.com.crux.to;

public class TributosMovimentacoesTO {

	private Long id;
	private Long idMovimentacao;
	private TributosTO tributo;
	private Double valor;
	private Long usuarioAlteracao;

	
	public TributosMovimentacoesTO() {
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdMovimentacao() {
		return idMovimentacao;
	}

	public void setIdMovimentacao(Long idMovimentacao) {
		this.idMovimentacao = idMovimentacao;
	}

	public TributosTO getTributo() {
		return tributo;
	}

	public void setTributo(TributosTO tributo) {
		this.tributo = tributo;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public Long getUsuarioAlteracao() {
		return usuarioAlteracao;
	}

	public void setUsuarioAlteracao(Long usuarioAlteracao) {
		this.usuarioAlteracao = usuarioAlteracao;
	}
	
}