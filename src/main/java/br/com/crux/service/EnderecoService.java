package br.com.crux.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.crux.cmd.GetEnderecoPorCepCmd;
import br.com.crux.cmd.GetEstadosCmd;
import br.com.crux.to.CepTO;

@RestController
@RequestMapping(value = "endereco")
public class EnderecoService {

	@Autowired private GetEstadosCmd getEstadosCmd;
	@Autowired private GetEnderecoPorCepCmd getEnderecoPorCepCmd;
	
	@GetMapping(path = "/estados", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<String> getAllEstados() {
		return getEstadosCmd.getAllEstados();
	}
	
	
	@GetMapping(path = "/cep/{cep}", produces = MediaType.APPLICATION_JSON_VALUE)
	public CepTO getCep(@PathVariable(name = "cep") String cep) {
		return getEnderecoPorCepCmd.getEndereco(cep);
	}
	
	
}
