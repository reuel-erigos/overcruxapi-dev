package br.com.crux.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.crux.cmd.AlterarProgramaCmd;
import br.com.crux.cmd.CadastrarProgramaCmd;
import br.com.crux.cmd.ExcluirProgramaCmd;
import br.com.crux.cmd.GetProgramaCmd;
import br.com.crux.to.ProgramaTO;

@RestController
@RequestMapping(value = "programa")
public class ProgramaService {

	@Autowired private GetProgramaCmd getCmd;
	@Autowired private ExcluirProgramaCmd excluirCmd;
	@Autowired private AlterarProgramaCmd alterarCmd;
	@Autowired private CadastrarProgramaCmd cadastrarCmd;

	@GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<ProgramaTO> getAll() {
		return getCmd.getAll();
	}
	
	@GetMapping(path = "/instituicao/logada", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<ProgramaTO> getAllProgramasIntituicaoLogada() {
		return getCmd.getAllProgramasIntituicaoLogada();
	}	

	@GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ProgramaTO getById(@PathVariable(name = "id") Long id) {
		return getCmd.getTOById(id);
	}

	@PostMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE)
	@Transactional
	public void cadastrar(@RequestBody ProgramaTO to) {
		cadastrarCmd.cadastrar(to);
	}

	@PutMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE)
	@Transactional
	public void alterar(@RequestBody ProgramaTO to) {
		alterarCmd.alterar(to);
	}

	@DeleteMapping(path = "/{id}")
	@Transactional
	public void excluir(@PathVariable(name = "id") Long id) {
		excluirCmd.excluir(id);
	}

}
