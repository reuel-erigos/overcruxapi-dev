package br.com.crux.to.filtro;

import java.util.List;

public class FiltroArquivoTO {

	private String tipo;
	
	private Long idInstituicao;
	
	private List<String> tipos;

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Long getIdInstituicao() {
		return idInstituicao;
	}

	public void setIdInstituicao(Long idInstituicao) {
		this.idInstituicao = idInstituicao;
	}

	public List<String> getTipos() {
		return tipos;
	}

	public void setTipos(List<String> tipos) {
		this.tipos = tipos;
	}
	
	
}
