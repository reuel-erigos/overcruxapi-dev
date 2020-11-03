package br.com.crux.cmd;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.excel.ExportacaoDadosAlunoExcelFileExporter;
import br.com.crux.to.exportacao.ListaCompletaDadosExportar;

@Component
public class GerarArquivoExportacaoDadosAlunoCmd {

	@Autowired private ExportacaoDadosAlunoExcelFileExporter excelFileExporter;

	public byte[] gerar(List<ListaCompletaDadosExportar> dados) {
		return excelFileExporter.gerar(dados);
	}


}
