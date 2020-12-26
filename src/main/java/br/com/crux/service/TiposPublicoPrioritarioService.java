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

import br.com.crux.cmd.AlterarTiposPublicoPrioritarioCmd;
import br.com.crux.cmd.CadastrarTiposPublicoPrioritarioCmd;
import br.com.crux.cmd.ExcluirTiposPublicoPrioritarioCmd;
import br.com.crux.cmd.GetTiposPublicoPrioritarioCmd;
import br.com.crux.to.TiposPublicoPrioritarioTO;

@RestController
@RequestMapping(value = "tipospublicoprioritario")
public class TiposPublicoPrioritarioService {

	@Autowired private GetTiposPublicoPrioritarioCmd getCmd;
	@Autowired private CadastrarTiposPublicoPrioritarioCmd cadastrarCmd;
	@Autowired private AlterarTiposPublicoPrioritarioCmd alterarCmd;
	@Autowired private ExcluirTiposPublicoPrioritarioCmd excluirCmd;
	
	@GetMapping
	public List<TiposPublicoPrioritarioTO> getAll() {
		return getCmd.getAll();
	}

	@GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public TiposPublicoPrioritarioTO getById(@PathVariable(name = "id") Long id) {
		return getCmd.getTOById(id);
	}

	@PostMapping
	@Transactional
	public void cadastrar(@RequestBody TiposPublicoPrioritarioTO to) {
		cadastrarCmd.cadastrar(to);
	}

	@PutMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE)
	@Transactional
	public void alterar(@RequestBody TiposPublicoPrioritarioTO to) {
		alterarCmd.alterar(to);
	}

	@DeleteMapping(path = "/{id}")
	@Transactional
	public void excluir(@PathVariable(name = "id") Long id) {
		excluirCmd.excluir(id);
	}

}
