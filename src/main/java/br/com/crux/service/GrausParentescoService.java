package br.com.crux.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.crux.cmd.GetGrausParentescoCmd;
import br.com.crux.to.GrausParentescoTO;

@RestController
@RequestMapping(value = "grausparentesco")
public class GrausParentescoService {
	
	@Autowired
	private GetGrausParentescoCmd getCmd;
	
	@GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<GrausParentescoTO> getAll() {
		return getCmd.getAll();
	}
	
	@GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public GrausParentescoTO getById(@PathVariable(name = "id") Long id) {
		return getCmd.getTOById(id);
	}
	
	
	

}
