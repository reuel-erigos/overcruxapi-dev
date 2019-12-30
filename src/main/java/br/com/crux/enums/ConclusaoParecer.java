package br.com.crux.enums;

public enum ConclusaoParecer {
	// S = Dar continuidade ao processo seletivo; n = Não dar continuidade ao
	// processo seletivo

	CONTINUAR(1, "S", "Dar continuidade ao processo seletivo"),
	NAO_CONTINUAR(2, "N", "Não dar continuidade ao processo seletivo");

	private Integer id;
	private String tipo;
	private String descricao;

	private ConclusaoParecer(Integer id, String tipo, String descricao) {
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

	public static ConclusaoParecer getPorTipo(String tipo) {
		for (ConclusaoParecer tipoClassificador : values()) {
			if (tipoClassificador.getTipo().equals(tipo)) {
				return tipoClassificador;
			}
		}
		return null;
	}

}
