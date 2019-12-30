package br.com.crux.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.crux.cmd.AlterarUniformesAlunoCmd;
import br.com.crux.cmd.GetUniformesAlunoCmd;
import br.com.crux.to.UniformesAlunoTO;

@RestController
@RequestMapping(value = "uniformealuno")
public class UniformesAlunoService {
	
	@Autowired
	private GetUniformesAlunoCmd getCmd;
	@Autowired
	private AlterarUniformesAlunoCmd alterarCmd;
	
	
	@GetMapping(path = "/matriculado/atividade/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<UniformesAlunoTO> getAllAlunosMatriculadosNaAtividadeNoPeriodo(@PathVariable(name = "id") Long idAtividade) {
		return getCmd.getAllAlunosMatriculadosTO(idAtividade);
	}
	
	
	@GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public UniformesAlunoTO getById(@PathVariable(name = "id") Long id) {
		return getCmd.getById(id);
	}
	
	@PutMapping(path = "/atividade/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void alterar(@PathVariable(name = "id") Long idAtividade,
			            @RequestBody List<UniformesAlunoTO> param) {
		alterarCmd.alterarAll(param, idAtividade);
	}
	
}
