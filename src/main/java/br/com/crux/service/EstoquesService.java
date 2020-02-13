package br.com.crux.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.crux.cmd.GetEstoquesCmd;
import br.com.crux.to.EstoquesTO;

@RestController
@RequestMapping(value = "estoques")
public class EstoquesService {

	@Autowired private GetEstoquesCmd getCmd;

	
	@GetMapping(path = "/filter", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<EstoquesTO> getAllFilter(@RequestParam(name = "material", required = false) Long idMaterial,
                                                @RequestParam(name = "programa", required = false) Long idPrograma,
                                                @RequestParam(name = "projeto", required = false) Long idProjeto) {
		return getCmd.getAllFilter(idMaterial, idPrograma, idProjeto);
	}
	
	
	@GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public EstoquesTO getById(@PathVariable(name = "id") Long id) {
		return getCmd.getTOById(id);
	}

}
