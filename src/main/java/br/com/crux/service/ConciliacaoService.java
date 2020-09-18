package br.com.crux.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.crux.cmd.GerarConciliacaoCmd;
import br.com.crux.cmd.GetConciliacaoCmd;
import br.com.crux.to.ConciliacaoTO;

@RestController
@RequestMapping(value = "conciliacao")
public class ConciliacaoService {
	
	@Autowired private GetConciliacaoCmd getCmd;
	@Autowired private GerarConciliacaoCmd gerarCmd;

	@GetMapping(path = "/filter", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<ConciliacaoTO> getAllFilter(@RequestParam(name = "dataInicio", required = false) Long dataInicio,
                                            @RequestParam(name = "dataFim", required = false) Long dataFim,
                                            @RequestParam(name = "contaBancaria", required = false) Long idContaBancaria
                                            ) {
		return getCmd.getAllFilter(dataInicio, dataFim, idContaBancaria);
	}

	
	@PostMapping(path = "/exportar")
	public void gerar(@RequestParam(name = "dataInicio", required = false) Long dataInicio,
                      @RequestParam(name = "dataFim", required = false) Long dataFim,
                      @RequestParam(name = "contaBancaria", required = false) Long idContaBancaria
                      ) {
		gerarCmd.gerar(dataInicio, dataFim, idContaBancaria);
	}
}
