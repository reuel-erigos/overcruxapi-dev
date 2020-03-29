package br.com.crux.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.crux.cmd.GetExtratoContaBancariaCmd;
import br.com.crux.to.ExtratoTO;

@RestController
@RequestMapping("extrato")
public class ExtratoContaBancariaService {
	
	@Autowired
	private GetExtratoContaBancariaCmd getExtratoContaBancariaCmd;
	
 
    @GetMapping(path = "/conta-bancaria/{id}/{dataInicio}/{dataFim}",  produces = MediaType.APPLICATION_JSON_VALUE)
    public ExtratoTO getExtrato(@PathVariable(name = "id") Long idContaBancaria, 
                         		@PathVariable(name = "dataInicio") String dataInicio,
                        		@PathVariable(name = "dataFim") String dataFim) {
    	return getExtratoContaBancariaCmd.getExtrato(idContaBancaria, dataInicio, dataFim);
    }

}
