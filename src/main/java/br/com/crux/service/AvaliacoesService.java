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

import br.com.crux.cmd.AlterarAvaliacoesCmd;
import br.com.crux.cmd.CadastrarAvaliacoesCmd;
import br.com.crux.cmd.ExcluirAvaliacoesCmd;
import br.com.crux.cmd.GetAvaliacoesCmd;
import br.com.crux.to.AvaliacoesTO;

@RestController
@RequestMapping(value = "avaliacoes")
public class AvaliacoesService {

	@Autowired private GetAvaliacoesCmd getCmd;
	@Autowired private ExcluirAvaliacoesCmd excluirCmd;
	@Autowired private AlterarAvaliacoesCmd alterarCmd;
	@Autowired private CadastrarAvaliacoesCmd cadastrarCmd;

	@GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<AvaliacoesTO> getAll() {
		return getCmd.getAll();
	}

	@GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public AvaliacoesTO getById(@PathVariable(name = "id") Long id) {
		return getCmd.getTOById(id);
	}


	@PostMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void cadastrar(@RequestBody AvaliacoesTO param) {
		cadastrarCmd.cadastrar(param);
	}

	@PutMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void alterar(@RequestBody AvaliacoesTO param) {
		alterarCmd.alterar(param);
	}

	@DeleteMapping(path = "/{id}")
	public void excluir(@PathVariable(name = "id") Long id) {
		excluirCmd.excluir(id);
	}

}
