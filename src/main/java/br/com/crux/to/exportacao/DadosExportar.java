package br.com.crux.to.exportacao;

import java.util.List;

public class DadosExportar {

	private String entidade;
	private List<GrupoDadosExportar> dados;

	public DadosExportar() {
	}

	
	public String getEntidade() {
		return entidade;
	}


	public void setEntidade(String entidade) {
		this.entidade = entidade;
	}


	public List<GrupoDadosExportar> getDados() {
		return dados;
	}

	public void setDados(List<GrupoDadosExportar> dados) {
		this.dados = dados;
	}
	
	
}
