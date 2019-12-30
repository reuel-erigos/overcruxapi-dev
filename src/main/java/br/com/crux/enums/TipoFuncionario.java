package br.com.crux.enums;

public enum TipoFuncionario {

	// F = FUNCIONÁRIO; E = ESTAGIÁRIO; C = COLABORADOR; I = INSTRUTOR; V =
	// VOLUNTÁRIO; D = CANDIDATO A VAGA DE FUNCIONÁRIO

	FUNCIONARIO(1, "F", "Funcionário"), VOLUNTARIO(2, "V", "Voluntário"), ESTAGIARIO(3, "E", "Estagiário"),
	COLABORADOR(4, "C", "Colaborador"), INSTRUTOR(5, "I", "Instrutor"),
	CANDIDATO_VAGA_FUNCIONARIO(6, "D", "Candidato a vaga de funcionário");

	private Integer id;
	private String tipo;
	private String descricao;

	private TipoFuncionario(Integer id, String tipo, String descricao) {
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

	public static TipoFuncionario getPorTipo(String tipo) {
		for (TipoFuncionario tipoClassificador : values()) {
			if (tipoClassificador.getTipo().equals(tipo)) {
				return tipoClassificador;
			}
		}
		return null;
	}

}
