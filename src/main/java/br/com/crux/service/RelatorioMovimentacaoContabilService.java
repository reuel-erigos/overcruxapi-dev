package br.com.crux.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.crux.cmd.GetMovimentacaoContabilCmd;
import br.com.crux.cmd.relatorios.financeiro.GerarRelatorioMovimentacaoContabilCmd;
import br.com.crux.to.relatorios.financeiro.MovimentacaoContabilTO;

@RestController
@RequestMapping(value = "movimentacaocontabil")
public class RelatorioMovimentacaoContabilService {

	@Autowired private GerarRelatorioMovimentacaoContabilCmd gerarRelatorioCmd;
	@Autowired private GetMovimentacaoContabilCmd getCmd;
	
	
	@PostMapping(path = "/mimetype/{mimetype}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public byte[] gerar(@RequestBody List<MovimentacaoContabilTO> dados, 
			            @PathVariable(name = "mimetype") String mimeType) {
		return gerarRelatorioCmd.gerar(dados, mimeType);
	}
	
	
	@GetMapping(path = "/saldocontacontabil", produces = MediaType.APPLICATION_JSON_VALUE)
	public Double getSaldoContaContabil(@RequestParam(name = "idPlanoConta", required = true) Long idPlanoConta,
			                            @RequestParam(name = "data", required = true) @DateTimeFormat(iso = ISO.DATE) LocalDate data
		                               ) {
		return getCmd.getSaldoContaContabil(idPlanoConta, data);
	}	
	
	@GetMapping(path = "/filter", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<MovimentacaoContabilTO> getAllFilter(@RequestParam(name = "idcategoria", required = false) Long idcategoria,
		                                             @RequestParam(name = "idprograma", required = false) Long idPrograma,
		                                             @RequestParam(name = "idprojeto", required = false) Long idProjeto,
		                                             @RequestParam(name = "dataInicio", required = false) Long dataInicio,
		                                             @RequestParam(name = "dataFim", required = false) Long dataFim
		                                            ) {
		return getCmd.getAllFilter(idcategoria, idPrograma, idProjeto, dataInicio, dataFim);
	}	
}
