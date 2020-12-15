package br.com.crux.to.relatorios.beneficiarios;

import java.util.List;

public class DadosObservacaoRelatorio {
	
	private List<Long> listaIdsAlunos;
	private String textoObservacao;
	private String tipoRelatorio;
	
	public DadosObservacaoRelatorio() {
	}

	public List<Long> getListaIdsAlunos() {
		return listaIdsAlunos;
	}

	public void setListaIdsAlunos(List<Long> listaIds) {
		this.listaIdsAlunos = listaIds;
	}

	public String getTextoObservacao() {
		return textoObservacao;
	}

	public void setTextoObservacao(String textoObservacao) {
		this.textoObservacao = textoObservacao;
	}

	public String getTipoRelatorio() {
		return tipoRelatorio;
	}

	public void setTipoRelatorio(String tipoRelatorio) {
		this.tipoRelatorio = tipoRelatorio;
	}
	
	
}
