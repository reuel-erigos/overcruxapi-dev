package br.com.crux.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.crux.cmd.GetDependentesCmd;
import br.com.crux.to.DependentesTO;

@RestController
@RequestMapping(value = "dependentes")
public class DependentesService {

	@Autowired private GetDependentesCmd getCmd;


	@GetMapping(path = "/pessoafisica/{cpf}", produces = MediaType.APPLICATION_JSON_VALUE)
	public DependentesTO getByCPF(@PathVariable(name = "cpf") Long cpf) {
		return getCmd.getByCPF(cpf);
	}
	


}
