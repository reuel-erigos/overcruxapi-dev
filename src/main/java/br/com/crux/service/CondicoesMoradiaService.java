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

import br.com.crux.cmd.AlterarCondicoesMoradiaCmd;
import br.com.crux.cmd.CadastrarCondicoesMoradiaCmd;
import br.com.crux.cmd.ExcluirCondicoesMoradiaCmd;
import br.com.crux.cmd.GetCondicoesMoradiaCmd;
import br.com.crux.to.CondicoesMoradiaTO;

@RestController
@RequestMapping(value = "condicaomoradia")
public class CondicoesMoradiaService {
	
	@Autowired
	private GetCondicoesMoradiaCmd getCmd;
	@Autowired
	private ExcluirCondicoesMoradiaCmd excluirCmd;
	@Autowired
	private AlterarCondicoesMoradiaCmd alterarCmd;
	@Autowired
	private CadastrarCondicoesMoradiaCmd cadastrarCmd;
	
	
	@GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<CondicoesMoradiaTO> getAll() {
		return getCmd.getAll();
	}
	
	@GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public CondicoesMoradiaTO getById(@PathVariable(name = "id") Long id) {
		return getCmd.getTOById(id);
	}
	
	@PostMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void cadastrar(@RequestBody CondicoesMoradiaTO param) {
		cadastrarCmd.cadastrar(param);
	}
	
	@PutMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void alterar(@RequestBody CondicoesMoradiaTO param) {
		alterarCmd.alterar(param);
	}
	
	@DeleteMapping(path = "/{id}")
	public void excluir(@PathVariable(name = "id") Long id) {
		excluirCmd.excluir(id);
	}

}
