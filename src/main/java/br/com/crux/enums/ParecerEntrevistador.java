package br.com.crux.enums;

public enum ParecerEntrevistador {

	// D = CANDIDATO A VAGA DE FUNCIONÁRIO; T= Totalmente adequado ao perfil; N =
	// Não adequado ao perfil; A = Adequado ao perfil; I = Indicado p/ consulta
	// futura)

	INDICADO_CONSULTA_FUTURA(1, "I", "Indicado p/ consulta futura"), ADEQUADO_PERFIL(2, "A", "Adequado ao perfil"),
	NAO_ADEQUADO_PERFIL(3, "N", "Não adequado ao perfil"),
	TOTALMENTO_ADEQUADO_PERFIL(4, "T", "Totalmente adequado ao perfil"),
	CANDIDATO_VAGA_FUNCIONARIO(5, "D", "Candidato a vaga de funcionário");

	private Integer id;
	private String tipo;
	private String descricao;

	private ParecerEntrevistador(Integer id, String tipo, String descricao) {
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

	public static ParecerEntrevistador getPorTipo(String tipo) {
		for (ParecerEntrevistador tipoClassificador : values()) {
			if (tipoClassificador.getTipo().equals(tipo)) {
				return tipoClassificador;
			}
		}
		return null;
	}

}
