package br.com.crux.enums;

public enum TipoCargo {

	// F=FUNCIONÁRIO; V = VOLUNTÁRIO; E = ESTAGIÁRIO

	FUNCIONARIO(1, "F", "Funcionário"), VOLUNTARIO(2, "V", "Voluntário"), ESTAGIARIO(3, "E", "Estagiário");

	private Integer id;
	private String tipo;
	private String descricao;

	private TipoCargo(Integer id, String tipo, String descricao) {
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

	public static TipoCargo getPorTipo(String tipo) {
		for (TipoCargo tipoClassificador : values()) {
			if (tipoClassificador.getTipo().equals(tipo)) {
				return tipoClassificador;
			}
		}
		return null;
	}
	
	
	public static TipoCargo getPorTipo(Integer tipo) {
		try {
			Integer valor = Integer.valueOf(tipo);
			return getPorTipo(valor);
		} catch (NumberFormatException e) {
			return null;
		}
	}

}
