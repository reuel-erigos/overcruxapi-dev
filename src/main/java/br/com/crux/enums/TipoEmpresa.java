package br.com.crux.enums;

public enum TipoEmpresa {

	// P = PARCEIRA; V = CONVÊNIO; T = CONTRATO; F = FORNECEDOR; C = CLIENTE; O =
	// OUTRO)

	PARCEIRA(1, "P", "Parceria"), CONVÊNIO(2, "V", "Convênio"), CONTRATO(3, "T", "Contrato"),
	FORNECEDOR(4, "F", "Fornecedor"), CLIENTE(5, "C", "Cliente"), OUTRO(6, "O", "Outro");

	private Integer id;
	private String tipo;
	private String descricao;

	private TipoEmpresa(Integer id, String tipo, String descricao) {
		this.id = id;
		this.tipo = tipo;
		this.descricao = descricao;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public static TipoEmpresa getPorTipo(String tipo) {
		for (TipoEmpresa tipoClassificador : values()) {
			if (tipoClassificador.getTipo().equals(tipo)) {
				return tipoClassificador;
			}
		}
		return null;
	}

}
