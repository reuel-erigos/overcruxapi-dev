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

import br.com.crux.cmd.AlterarFuncoesCmd;
import br.com.crux.cmd.CadastrarFuncoesCmd;
import br.com.crux.cmd.ExcluirFuncoesCmd;
import br.com.crux.cmd.GetFuncoesCmd;
import br.com.crux.to.FuncoesTO;

@RestController
@RequestMapping(value = "funcoes")
public class FuncoesService {

	@Autowired private GetFuncoesCmd getCmd;
	@Autowired private CadastrarFuncoesCmd cadastrarCmd;
	@Autowired private AlterarFuncoesCmd alterarCmd;
	@Autowired private ExcluirFuncoesCmd excluirCmd;

	@GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<FuncoesTO> getAll() {
		return getCmd.getAll();
	}

	@GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public FuncoesTO getById(@PathVariable(name = "id") Long id) {
		return getCmd.getTOById(id);
	}

	@PostMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void cadastrar(@RequestBody FuncoesTO funcoes) {
		cadastrarCmd.cadastrar(funcoes);
	}

	@PutMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void alterar(@RequestBody FuncoesTO funcoes) {
		alterarCmd.alterar(funcoes);
	}

	@DeleteMapping(path = "/{id}")
	public void excluir(@PathVariable(name = "id") Long id) {
		excluirCmd.excluir(id);
	}

}
