package br.com.crux.enums;

public enum TipoUnidade {

	MATRIZ(1, "M"), FILIAL(2, "F");

	private Integer id;
	private String tipo;

	private TipoUnidade(Integer id, String tipo) {
		this.id = id;
		this.tipo = tipo;
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

	public static TipoUnidade getPorTipo(String tipo) {
		for (TipoUnidade tipoUnidade : values()) {
			if (tipoUnidade.getTipo().equals(tipo)) {
				return tipoUnidade;
			}
		}
		return null;
	}

}
