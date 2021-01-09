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

import br.com.crux.cmd.GetNormativaPagamentosCmd;
import br.com.crux.cmd.relatorios.financeiro.GerarRelatorioNormativaPagamentosCmd;
import br.com.crux.to.relatorios.financeiro.NormativaPagamentosTO;

@RestController
@RequestMapping(value = "normativapagamentos")
public class RelatorioNormativaPagamentosService {

	@Autowired private GerarRelatorioNormativaPagamentosCmd gerarRelatorioNormativaPagamentosCmd;
	@Autowired private GetNormativaPagamentosCmd getCmd;
	
	
	@PostMapping(path = "/mimetype/{mimetype}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public byte[] gerar(@RequestBody List<NormativaPagamentosTO> dados, 
			            @PathVariable(name = "mimetype") String mimeType) {
		return gerarRelatorioNormativaPagamentosCmd.gerar(dados, mimeType);
	}
	
	
	@GetMapping(path = "/filter", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<NormativaPagamentosTO> getAllFilter(@RequestParam(name = "categoria", required = false) String categoria,
		                                            @RequestParam(name = "cnpj_cpf", required = false) String cnpjCpf,
		                                            @RequestParam(name = "programaProjeto", required = false) String programaProjeto,
		                                            @RequestParam(name = "dataInicio", required = false) Long dataInicio,
		                                            @RequestParam(name = "dataFim", required = false) Long dataFim
		                                            ) {
		return getCmd.getAllFilter(categoria, cnpjCpf, programaProjeto, dataInicio, dataFim);
	}
}
