package br.com.crux.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.crux.cmd.relatorios.GerarFichaMatriculaCmd;
import br.com.crux.cmd.relatorios.ParametrosTO;
import br.com.crux.infra.relatorio.TipoRelatorio;

@RestController
@RequestMapping(value = "fichamatricula")
public class RelatorioService {

	@Autowired
	private GerarFichaMatriculaCmd gerarFichaMatriculaCmd;
	
	@PostMapping(path = "/pdf", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_PDF_VALUE)
	public byte[] gerarPDF(@RequestBody List<ParametrosTO> param) {
		return gerarFichaMatriculaCmd.gerarPDF(param, TipoRelatorio.PDF);
	}
	
	@PostMapping(path = "/xls", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public byte[] gerarXLS(@RequestBody List<ParametrosTO> param) {
		return gerarFichaMatriculaCmd.gerarPDF(param, TipoRelatorio.XLS);
	}
}
