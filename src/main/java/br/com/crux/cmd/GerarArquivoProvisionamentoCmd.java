package br.com.crux.cmd;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.excel.ProvisionamentoExcelFileExporter;
import br.com.crux.to.ProvisionamentoTO;

@Component
public class GerarArquivoProvisionamentoCmd {

	@Autowired private AjustarProvisionamentoCmd ajustarCmd;
	@Autowired private ProvisionamentoExcelFileExporter excelFileExporter;

	public byte[] gerar(List<ProvisionamentoTO> provisoes) {
		ajustarCmd.ajustar(provisoes);
		return excelFileExporter.gerar(provisoes);
	}


}
