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

import br.com.crux.cmd.GetFaturasPagarCmd;
import br.com.crux.cmd.relatorios.financeiro.GerarRelatorioFaturasPagarCmd;
import br.com.crux.to.relatorios.financeiro.FaturasPagarTO;

@RestController
@RequestMapping(value = "faturaspagar")
public class RelatorioFaturasPagarService {

	@Autowired private GerarRelatorioFaturasPagarCmd gerarRelatorioCmd;
	@Autowired private GetFaturasPagarCmd getCmd;
	
	
	@PostMapping(path = "/mimetype/{mimetype}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public byte[] gerar(@RequestBody List<FaturasPagarTO> dados, 
			            @PathVariable(name = "mimetype") String mimeType) {
		return gerarRelatorioCmd.gerar(dados, mimeType);
	}
	
	
	@GetMapping(path = "/filter", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<FaturasPagarTO> getAllFilter(@RequestParam(name = "idcategoria", required = false) Long idcategoria,
		                                     @RequestParam(name = "cnpj_cpf", required = false) String cnpjCpf,
		                                     @RequestParam(name = "programaProjeto", required = false) String programaProjeto,
		                                     @RequestParam(name = "dataInicio", required = false) Long dataInicio,
		                                     @RequestParam(name = "dataFim", required = false) Long dataFim,
		                                     @RequestParam(name = "dataInicioVenc", required = false) Long dataInicioVenc,
		                                     @RequestParam(name = "dataFimVenc", required = false) Long dataFimVenc
		                                     ) {
		return getCmd.getAllFilter(idcategoria, cnpjCpf, programaProjeto, dataInicio, dataFim, dataInicioVenc, dataFimVenc);
	}
}
