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

import br.com.crux.cmd.AlterarMetasCmd;
import br.com.crux.cmd.CadastrarMetasCmd;
import br.com.crux.cmd.ExcluirMetasCmd;
import br.com.crux.cmd.GetMetasCmd;
import br.com.crux.to.MetasTO;

@RestController
@RequestMapping(value = "metas")
public class MetasService {

	@Autowired private GetMetasCmd getMetasCmd;
	@Autowired private ExcluirMetasCmd excluirMetasCmd;
	@Autowired private AlterarMetasCmd alterarMetasCmd;
	@Autowired private CadastrarMetasCmd cadastrarMetasCmd;

	@GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<MetasTO> getAll() {
		return getMetasCmd.getAll();
	}

	@GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public MetasTO getById(@PathVariable(name = "id") Long id) {
		return getMetasCmd.getById(id);
	}

	@PostMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void cadastrar(@RequestBody MetasTO to) {
		cadastrarMetasCmd.cadastrar(to);
	}

	@PutMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void alterar(@RequestBody MetasTO to) {
		alterarMetasCmd.alterar(to);
	}

	@DeleteMapping(path = "/{id}")
	public void excluir(@PathVariable(name = "id") Long id) {
		excluirMetasCmd.excluir(id);
	}

}
