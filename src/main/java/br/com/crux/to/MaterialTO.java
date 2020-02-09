package br.com.crux.to;

public class MaterialTO {

	private Long id;
	private String nome;
	private String tipo;
	private String nomeNotaFiscal;
	private String codigoUnidadeMedida;
	private String descricaoUnidadeMedida;
	private Integer quantidadeLimitePedido;
	private Integer numeroVidaUtil;
	private Integer valorDepreciacao;

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

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getNomeNotaFiscal() {
		return nomeNotaFiscal;
	}

	public void setNomeNotaFiscal(String nomeNotaFiscal) {
		this.nomeNotaFiscal = nomeNotaFiscal;
	}

	public String getCodigoUnidadeMedida() {
		return codigoUnidadeMedida;
	}

	public void setCodigoUnidadeMedida(String codigoUnidadeMedida) {
		this.codigoUnidadeMedida = codigoUnidadeMedida;
	}

	public String getDescricaoUnidadeMedida() {
		return descricaoUnidadeMedida;
	}

	public void setDescricaoUnidadeMedida(String descricaoUnidadeMedida) {
		this.descricaoUnidadeMedida = descricaoUnidadeMedida;
	}


	public Integer getQuantidadeLimitePedido() {
		return quantidadeLimitePedido;
	}

	public void setQuantidadeLimitePedido(Integer quantidadeLimitePedido) {
		this.quantidadeLimitePedido = quantidadeLimitePedido;
	}

	public Integer getNumeroVidaUtil() {
		return numeroVidaUtil;
	}

	public void setNumeroVidaUtil(Integer numeroVidaUtil) {
		this.numeroVidaUtil = numeroVidaUtil;
	}

	public Integer getValorDepreciacao() {
		return valorDepreciacao;
	}

	public void setValorDepreciacao(Integer valorDepreciacao) {
		this.valorDepreciacao = valorDepreciacao;
	}
	
	

}
