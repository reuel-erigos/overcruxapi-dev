package br.com.crux.enums;

public enum TipoQuestionario {

	// Classificação do tipo do questionario (T = TALENTO; G = GRUPO FAMILIAR;  O = OUTRO)

	TALENTO(1, "T", "Talento"), GRUPO_FAMILIAR(2, "G", "Grupo Familiar"), OUTRO(3, "O", "Outro");

	private Integer id;
	private String tipo;
	private String descricao;

	private TipoQuestionario(Integer id, String tipo, String descricao) {
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

	public static TipoQuestionario getPorTipo(String tipo) {
		for (TipoQuestionario tipoClassificador : values()) {
			if (tipoClassificador.getTipo().equals(tipo)) {
				return tipoClassificador;
			}
		}
		return null;
	}

}
