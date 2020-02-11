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

import br.com.crux.cmd.AlterarCategoriasContabeisCmd;
import br.com.crux.cmd.CadastrarCategoriasContabeisCmd;
import br.com.crux.cmd.ExcluirFuncoesCmd;
import br.com.crux.cmd.GetCategoriasContabeisCmd;
import br.com.crux.to.CategoriasContabeisTO;

@RestController
@RequestMapping(value = "categoriascontabeis")
public class CategoriasContabeisService {

	@Autowired private GetCategoriasContabeisCmd getCmd;
	@Autowired private CadastrarCategoriasContabeisCmd cadastrarCmd;
	@Autowired private AlterarCategoriasContabeisCmd alterarCmd;
	@Autowired private ExcluirFuncoesCmd excluirCmd;

	@GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<CategoriasContabeisTO> getAll() {
		return getCmd.getAll();
	}

	@GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public CategoriasContabeisTO getById(@PathVariable(name = "id") Long id) {
		return getCmd.getTOById(id);
	}

	@PostMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void cadastrar(@RequestBody CategoriasContabeisTO to) {
		cadastrarCmd.cadastrar(to);
	}

	@PutMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void alterar(@RequestBody CategoriasContabeisTO to) {
		alterarCmd.alterar(to);
	}

	@DeleteMapping(path = "/{id}")
	public void excluir(@PathVariable(name = "id") Long id) {
		excluirCmd.excluir(id);
	}

}