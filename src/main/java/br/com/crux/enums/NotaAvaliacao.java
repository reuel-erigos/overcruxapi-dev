package br.com.crux.enums;

public enum NotaAvaliacao {

	/*
	//Número da avaliação do aluno  1: Inaceitável - Dê o grau 1 se você achar nenhuma evidência da competência 
	                                2: Insatisfatório - Dê o grau 2 se você achar pouca a evidência da competência 
	                                3: Médio - Dê o grau 3 se você achar média a evidência da competência 
	                                4: Bom - Dê o grau 4 se você achar boa a evidência da competência 
	                                5: Excelente -  Dê o grau 5 se você achar forte a evidência da competência
	*/ 

	INACEITAVEL(1, "1", "Inaceitável - Dê o grau 1 se você achar nenhuma evidência da competência"), 
	INSATISFATORIO(2, "2", "Insatisfatório - Dê o grau 2 se você achar pouca a evidência da competência"),
	INDUSTRIAS(3, "3", "Médio - Dê o grau 3 se você achar média a evidência da competência"), 
	OUTRO(4, "4", "Bom - Dê o grau 4 se você achar boa a evidência da competência"),
	EXCELENTE(5,"5","Excelente -  Dê o grau 5 se você achar forte a evidência da competência");

	private Integer id;
	private String tipo;
	private String descricao;

	private NotaAvaliacao(Integer id, String tipo, String descricao) {
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

	public static NotaAvaliacao getPorTipo(String tipo) {
		for (NotaAvaliacao tipoClassificador : values()) {
			if (tipoClassificador.getTipo().equals(tipo)) {
				return tipoClassificador;
			}
		}
		return null;
	}

}
