package br.com.crux.to.exportacao;

public class Coluna {
	
	private String nome;
	private String descricao;
	private boolean exportar;
	
	public Coluna() {
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public boolean isExportar() {
		return exportar;
	}

	public void setExportar(boolean exportar) {
		this.exportar = exportar;
	}

	
}
