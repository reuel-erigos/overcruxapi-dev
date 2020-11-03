package br.com.crux.to.exportacao;

import java.util.List;

import br.com.crux.to.ExportacaoDadosAlunoTO;

public class ListaCompletaDadosExportar {
	
	private List<ExportacaoDadosAlunoTO> listaDadosExportacao;
	private List<DadosExportar> exportarDados;

	public ListaCompletaDadosExportar() {
	}

	public List<ExportacaoDadosAlunoTO> getListaDadosExportacao() {
		return listaDadosExportacao;
	}

	public void setListaDadosExportacao(List<ExportacaoDadosAlunoTO> listaDadosExportacao) {
		this.listaDadosExportacao = listaDadosExportacao;
	}

	public List<DadosExportar> getExportarDados() {
		return exportarDados;
	}

	public void setExportarDados(List<DadosExportar> exportarDados) {
		this.exportarDados = exportarDados;
	}
	
	
}
