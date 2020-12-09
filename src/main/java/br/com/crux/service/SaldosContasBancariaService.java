package br.com.crux.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.crux.cmd.GetSaldosContasBancariaCmd;
import br.com.crux.dao.dto.SaldoContaBancariaDTO;
import br.com.crux.to.SaldosContasBancariaTO;

@RestController
@RequestMapping(value = "saldoscontasbancaria")
public class SaldosContasBancariaService {

	@Autowired private GetSaldosContasBancariaCmd getCmd;

	
	@GetMapping(path = "/filter", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<SaldosContasBancariaTO> getAllFilter(@RequestParam(name = "tipoContaBancaria", required = false) String tipoContaBancaria,
                                                     @RequestParam(name = "nomeBanco", required = false) String nomeBanco,
                                                     @RequestParam(name = "numeroAgencia", required = false) String numeroAgencia,
                                                     @RequestParam(name = "numeroContaBancaria", required = false) String numeroContaBancaria,
                                                     @RequestParam(name = "dataInicio", required = false) LocalDateTime dataInicio,
                                                     @RequestParam(name = "dataFim", required = false) LocalDateTime dataFim
                                                     ) {
		return getCmd.getAllFilter(tipoContaBancaria, nomeBanco, numeroAgencia, numeroContaBancaria, dataInicio, dataFim);
	}
	
	
	@GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public SaldosContasBancariaTO getById(@PathVariable(name = "id") Long id) {
		return getCmd.getTOById(id);
	}

	@GetMapping("/contabancaria/{id}")
	public SaldosContasBancariaTO getPorConta(@PathVariable Long id) {
		return getCmd.getPorConta(id);
	}

	
	@GetMapping(path = "/saldo/{contabancaria}", produces = MediaType.APPLICATION_JSON_VALUE)
	public SaldoContaBancariaDTO getSaldoContaBancaria(@PathVariable(name = "contabancaria") Long numeroContaBancaria,
													   @RequestParam(name = "dataInicio", required = true) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataInicio,
											           @RequestParam(name = "dataFim", required = true) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataFinal
			                                           ) {
		return getCmd.getSaldoContaBancaria(dataInicio, dataFinal, numeroContaBancaria);
	}
	
}
