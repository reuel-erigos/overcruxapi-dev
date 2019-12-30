package br.com.crux.enums;

public enum CategoriaEmpresa {

	// P = PRESTADOR DE SERVIÇOS; C = COMERCIAL; I = INDUSTRIAS; O = OUTRO)

	PRESTADOR_SERVICO(1, "P", "Prestador de Serviços"), COMERCIAL(2, "C", "Comercial"),
	INDUSTRIAS(3, "I", "Industrias"), OUTRO(4, "O", "Outro");

	private Integer id;
	private String tipo;
	private String descricao;

	private CategoriaEmpresa(Integer id, String tipo, String descricao) {
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

	public static CategoriaEmpresa getPorTipo(String tipo) {
		for (CategoriaEmpresa tipoClassificador : values()) {
			if (tipoClassificador.getTipo().equals(tipo)) {
				return tipoClassificador;
			}
		}
		return null;
	}

}
