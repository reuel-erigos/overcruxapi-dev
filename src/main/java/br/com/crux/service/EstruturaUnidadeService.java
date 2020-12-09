package br.com.crux.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.crux.cmd.GetEstruturaUnidadeCmd;
import br.com.crux.to.EstruturaUnidadeTO;

@RestController
@RequestMapping(value = "estruturasunidades")
public class EstruturaUnidadeService {

	@Autowired
	private GetEstruturaUnidadeCmd getCmd;

	@GetMapping
	public List<EstruturaUnidadeTO> getAll() {
		return getCmd.getAll();
	}

	@GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public EstruturaUnidadeTO getById(@PathVariable(name = "id") Long id) {
		return getCmd.getTOById(id);
	}

}
