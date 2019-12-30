package br.com.crux.enums;

public enum TipoResponsavel {

	// Classificador indicando que o tipo de responsável do aluno (F = FINANCEIRO ; P = PEDAGÓGICO; A=A AMBOS)

	FINANCEIRO(1, "F", "Financeiro"), 
	PEDAGOGICO(2, "P", "Pedagógico"),
	A_AMBOS(3, "A", "A ambos");

	private Integer id;
	private String tipo;
	private String descricao;

	private TipoResponsavel(Integer id, String tipo, String descricao) {
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

	public static TipoResponsavel getPorTipo(String tipo) {
		for (TipoResponsavel tipoClassificador : values()) {
			if (tipoClassificador.getTipo().equals(tipo)) {
				return tipoClassificador;
			}
		}
		return null;
	}

}
