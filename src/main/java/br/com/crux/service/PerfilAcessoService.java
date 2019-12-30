package br.com.crux.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.crux.cmd.GetPerfilAcessoCmd;
import br.com.crux.to.PerfilAcessoTO;

@RestController
@RequestMapping(value = "perfilacesso")
public class PerfilAcessoService {

	@Autowired
	private GetPerfilAcessoCmd getCmd;
	
	
	@GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<PerfilAcessoTO> getAll() {
		return getCmd.getAll();
	}
	
	@GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public PerfilAcessoTO getById(@PathVariable(name = "id") Long id) {
		return getCmd.getById(id);
	}

}
