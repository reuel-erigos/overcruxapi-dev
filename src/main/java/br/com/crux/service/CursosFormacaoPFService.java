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

import br.com.crux.cmd.AlterarCursosFormacaoPFCmd;
import br.com.crux.cmd.CadastrarCursosFormacaoPFCmd;
import br.com.crux.cmd.ExcluirCursosFormacaoPFCmd;
import br.com.crux.cmd.GetCursosFormacaoPFCmd;
import br.com.crux.to.CursosFormacaoPfTO;

@RestController
@RequestMapping(value = "cursoformacaopf")
public class CursosFormacaoPFService {
	
	@Autowired
	private GetCursosFormacaoPFCmd getCmd;
	@Autowired
	private ExcluirCursosFormacaoPFCmd  excluirCmd;
	@Autowired
	private AlterarCursosFormacaoPFCmd alterarCmd;
	@Autowired
	private CadastrarCursosFormacaoPFCmd cadastrarCmd;
	
	
	@GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<CursosFormacaoPfTO> getAll() {
		return getCmd.getAll();
	}
	
	@GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public CursosFormacaoPfTO getById(@PathVariable(name = "id") Long id) {
		return getCmd.getById(id);
	}
	
	@PostMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void cadastrar(@RequestBody CursosFormacaoPfTO param) {
		cadastrarCmd.cadastrar(param);
	}
	
	@PutMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void alterar(@RequestBody CursosFormacaoPfTO param) {
		alterarCmd.alterar(param);
	}
	
	@DeleteMapping(path = "/{id}")
	public void excluir(@PathVariable(name = "id") Long id) {
		excluirCmd.excluir(id);
	}
	
	
	
	
	

}
