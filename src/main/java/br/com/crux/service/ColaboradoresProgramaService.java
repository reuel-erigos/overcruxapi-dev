package br.com.crux.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.crux.cmd.ExcluirColaboradoresProgramaCmd;
import br.com.crux.cmd.GetColaboradoresProgramaCmd;
import br.com.crux.to.ColaboradoresProgramaTO;

@RestController
@RequestMapping(value = "colaboradoresprograma")
public class ColaboradoresProgramaService {

	@Autowired private GetColaboradoresProgramaCmd getCmd;
	@Autowired private ExcluirColaboradoresProgramaCmd excluirCmd;

	@GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ColaboradoresProgramaTO getById(@PathVariable(name = "id") Long id) {
		return getCmd.getById(id);
	}

	@DeleteMapping(path = "/{id}")
	public void excluir(@PathVariable(name = "id") Long id) {
		excluirCmd.excluir(id);
	}

}
