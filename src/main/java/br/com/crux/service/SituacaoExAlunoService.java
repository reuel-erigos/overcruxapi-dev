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

import br.com.crux.cmd.AlterarSituacaoExAlunoCmd;
import br.com.crux.cmd.CadastrarSituacaoExAlunoCmd;
import br.com.crux.cmd.ExcluirSituacaoExAlunoCmd;
import br.com.crux.cmd.GetSituacaoExAlunoCmd;
import br.com.crux.to.SituacaoExAlunoTO;

@RestController
@RequestMapping(value = "situacaoexaluno")
public class SituacaoExAlunoService {

	@Autowired private GetSituacaoExAlunoCmd getCmd;
	@Autowired private CadastrarSituacaoExAlunoCmd cadastrarCmd;
	@Autowired private AlterarSituacaoExAlunoCmd alterarCmd;
	@Autowired private ExcluirSituacaoExAlunoCmd excluirCmd;
	
	@GetMapping
	public List<SituacaoExAlunoTO> getAll() {
		return getCmd.getAll();
	}

	@GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public SituacaoExAlunoTO getById(@PathVariable(name = "id") Long id) {
		return getCmd.getTOById(id);
	}

	@PostMapping
	@Transactional
	public void cadastrar(@RequestBody SituacaoExAlunoTO to) {
		cadastrarCmd.cadastrar(to);
	}

	@PutMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE)
	@Transactional
	public void alterar(@RequestBody SituacaoExAlunoTO to) {
		alterarCmd.alterar(to);
	}

	@DeleteMapping(path = "/{id}")
	@Transactional
	public void excluir(@PathVariable(name = "id") Long id) {
		excluirCmd.excluir(id);
	}

}
