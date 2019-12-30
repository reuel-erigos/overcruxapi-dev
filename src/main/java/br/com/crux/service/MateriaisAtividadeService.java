package br.com.crux.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.crux.cmd.GetMateriaisAtividadeCmd;
import br.com.crux.to.MateriaisAtividadeTO;

@RestController
@RequestMapping(value = "materiaisatividade")
public class MateriaisAtividadeService {
	
	@Autowired
	private GetMateriaisAtividadeCmd getCmd;
	
	
	@GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<MateriaisAtividadeTO> getAll() {
		return getCmd.getAll();
	}
	
	@GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public MateriaisAtividadeTO getById(@PathVariable(name = "id") Long id) {
		return getCmd.getTOById(id);
	}

	@GetMapping(path = "/atividade/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<MateriaisAtividadeTO> getPorAtividade(@PathVariable(name = "id") Long id) {
		return getCmd.getAllTOPorAtividade(id);
	}
	

}
