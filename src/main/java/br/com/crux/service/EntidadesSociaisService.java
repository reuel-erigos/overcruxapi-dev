package br.com.crux.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.crux.cmd.AlterarEntidadesSociaisCmd;
import br.com.crux.cmd.CadastrarEntidadesSociaisCmd;
import br.com.crux.cmd.ExcluirEntidadesSociaisCmd;
import br.com.crux.cmd.GetEntidadesSociaisCmd;
import br.com.crux.to.EntidadesSociaisTO;

@RestController
@RequestMapping(value = "entidadessociais")
public class EntidadesSociaisService {
	
	@Autowired
	private GetEntidadesSociaisCmd getCmd;
	@Autowired
	private ExcluirEntidadesSociaisCmd  excluirCmd;
	@Autowired
	private AlterarEntidadesSociaisCmd alterarCmd;
	@Autowired
	private CadastrarEntidadesSociaisCmd cadastrarCmd;
	
	
	@GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<EntidadesSociaisTO> getAll() {
		return getCmd.getAll();
	}
	
	@GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public EntidadesSociaisTO getById(@PathVariable(name = "id") Long id) {
		return getCmd.getById(id);
	}
	
	@PostMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void cadastrar(@RequestBody EntidadesSociaisTO param) {
		cadastrarCmd.cadastrar(param);
	}
	
	@PutMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void alterar(@RequestBody EntidadesSociaisTO param) {
		alterarCmd.alterar(param);
	}
	
	@DeleteMapping(path = "/{id}")
	public void excluir(@PathVariable(name = "id") Long id) {
		excluirCmd.excluir(id);
	}

}
