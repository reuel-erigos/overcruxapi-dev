package br.com.crux.enums;

public enum SituacaoParentesco {

	// Classificador da situação do grau de parentesco (E = ESTREMECIDO; I = INTERROMPIDO; X = INEXISTENTE)

	ESTREMECIDO(1, "E", "Estremecido"),
	INTERROMPIDO(2, "I", "Interrompido"),
	INEXISTENTE(3, "X", "Inexistente");

	private Integer id;
	private String tipo;
	private String descricao;

	private SituacaoParentesco(Integer id, String tipo, String descricao) {
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

	public static SituacaoParentesco getPorTipo(String tipo) {
		for (SituacaoParentesco tipoClassificador : values()) {
			if (tipoClassificador.getTipo().equals(tipo)) {
				return tipoClassificador;
			}
		}
		return null;
	}

}
