package br.com.crux.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.crux.cmd.GetSaldoProjetoCmd;
import br.com.crux.cmd.relatorios.financeiro.GerarRelatorioSaldoProjetoCmd;
import br.com.crux.to.relatorios.financeiro.SaldoProjetoTO;

@RestController
@RequestMapping(value = "saldoprojetos")
public class RelatorioSaldoProjetoService {

	@Autowired private GerarRelatorioSaldoProjetoCmd gerarRelatorioCmd;
	@Autowired private GetSaldoProjetoCmd getCmd;
	
	
	@PostMapping(path = "/mimetype/{mimetype}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public byte[] gerar(@RequestBody List<SaldoProjetoTO> dados, 
			            @PathVariable(name = "mimetype") String mimeType) {
		return gerarRelatorioCmd.gerar(dados, mimeType);
	}
	
	
	@GetMapping(path = "/filter", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<SaldoProjetoTO> getAllFilter(@RequestParam(name = "idprograma", required = false) Long idPrograma,
									         @RequestParam(name = "idprojeto", required = false) Long idProjeto,
		                                     @RequestParam(name = "dataInicio", required = false) Long dataInicio,
		                                     @RequestParam(name = "dataFim", required = false) Long dataFim
		                                     ) {
		return getCmd.getAllFilter(idPrograma, idProjeto, dataInicio, dataFim);
	}
}
