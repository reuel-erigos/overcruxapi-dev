package br.com.crux.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.crux.cmd.GerarArquivoProvisionamentoCmd;
import br.com.crux.cmd.GetProvisionamentoCmd;
import br.com.crux.to.ProvisionamentoTO;



@RestController
@RequestMapping(value = "provisao")
public class ProvisionamentoService {
	
	@Autowired private GetProvisionamentoCmd getCmd;
	@Autowired private GerarArquivoProvisionamentoCmd gerarArquivoCmd;

	@GetMapping(path = "/filter", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<ProvisionamentoTO> getAllFilter(@RequestParam(name = "dataInicio", required = true) Long dataInicio,
                                                @RequestParam(name = "dataFim", required = true) Long dataFim,
                                                @RequestParam(name = "centrocusto", required = false) String nomeCentroCusto
                                                ) {
		return getCmd.getAllFilter(dataInicio, dataFim, nomeCentroCusto);
	}
	
	@GetMapping(path = "/inconsistentes", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<ProvisionamentoTO> getAllInconsistentes() {
		return getCmd.getAllInconsistentes();
	}

	
	@GetMapping(path = "/carregar", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<ProvisionamentoTO> carregar(@RequestParam(name = "dataInicio", required = true) Long dataInicio,
                                            @RequestParam(name = "dataFim", required = true) Long dataFim,
                                            @RequestParam(name = "centrocusto", required = false) String nomeCentroCusto
                                            ) {
		return getCmd.carregar(dataInicio, dataFim, nomeCentroCusto);
	}
	
	
	@PostMapping(path = "/gerar-arquivo", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public byte[] gerar(@RequestBody List<ProvisionamentoTO> param) {
		return gerarArquivoCmd.gerar(param);
	}
	
}
