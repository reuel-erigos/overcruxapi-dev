package br.com.crux.to.exportacao;

import java.util.List;

public class GrupoDadosExportar {
	
	private String nome;
	private String entidade;
	private boolean exportar;
	private List<Coluna> colunas;

	public GrupoDadosExportar() {
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEntidade() {
		return entidade;
	}

	public void setEntidade(String entidade) {
		this.entidade = entidade;
	}
	
	public boolean isExportar() {
		return exportar;
	}

	public void setExportar(boolean exportar) {
		this.exportar = exportar;
	}

	public List<Coluna> getColunas() {
		return colunas;
	}

	public void setColunas(List<Coluna> colunas) {
		this.colunas = colunas;
	}
	
	
}
