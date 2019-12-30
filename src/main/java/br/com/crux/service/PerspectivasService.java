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

import br.com.crux.cmd.AlterarPerspectivaCmd;
import br.com.crux.cmd.CadastrarPerspectivaCmd;
import br.com.crux.cmd.ExcluirPerspectivaCmd;
import br.com.crux.cmd.GetPerspectivasCmd;
import br.com.crux.to.PerspectivaTO;

@RestController
@RequestMapping(value = "perspectiva")
public class PerspectivasService {
	
	@Autowired
	private GetPerspectivasCmd getPerspectivasCmd;
	@Autowired
	private ExcluirPerspectivaCmd  excluirPerspectivaCmd;
	@Autowired
	private AlterarPerspectivaCmd alterarPerspectivaCmd;
	@Autowired
	private CadastrarPerspectivaCmd cadastrarPerspectivaCmd;
	
	
	@GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<PerspectivaTO> getAll() {
		return getPerspectivasCmd.getAll();
	}
	
	@GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public PerspectivaTO getById(@PathVariable(name = "id") Long id) {
		return getPerspectivasCmd.getById(id);
	}
	
	@PostMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void cadastrar(@RequestBody PerspectivaTO to) {
		cadastrarPerspectivaCmd.cadastrar(to);
	}
	
	@PutMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void alterar(@RequestBody PerspectivaTO to) {
		alterarPerspectivaCmd.alterar(to);
	}
	
	@DeleteMapping(path = "/{id}")
	public void excluir(@PathVariable(name = "id") Long id) {
		excluirPerspectivaCmd.excluir(id);
	}
	

}
