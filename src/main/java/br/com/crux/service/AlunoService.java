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

import br.com.crux.cmd.AlterarAlunoCmd;
import br.com.crux.cmd.CadastrarAlunoCmd;
import br.com.crux.cmd.ExcluirAlunoCmd;
import br.com.crux.cmd.GetAlunoCmd;
import br.com.crux.to.AlunoTO;

@RestController
@RequestMapping(value = "aluno")
public class AlunoService {
	
	@Autowired
	private GetAlunoCmd getCmd;
	@Autowired
	private ExcluirAlunoCmd  excluirCmd;
	@Autowired
	private AlterarAlunoCmd alterarCmd;
	@Autowired
	private CadastrarAlunoCmd cadastrarCmd;
	
	
	@GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<AlunoTO> getAll() {
		return getCmd.getAll();
	}
	
	@GetMapping(path = "/nome/{nome}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<AlunoTO> getAlunosByNomel(@PathVariable(name = "nome") String nome) {
		return getCmd.getAlunosByNome(nome);
	}
	
	@GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public AlunoTO getById(@PathVariable(name = "id") Long id) {
		return getCmd.getTOById(id);
	}
	
	@PostMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE)
	@Transactional
	public AlunoTO cadastrar(@RequestBody AlunoTO param) {
		return cadastrarCmd.cadastrar(param);
	}
	
	@PutMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE)
	@Transactional
	public AlunoTO alterar(@RequestBody AlunoTO param) {
		return alterarCmd.alterar(param);
	}
	
	@DeleteMapping(path = "/{id}")
	public void excluir(@PathVariable(name = "id") Long id) {
		excluirCmd.excluir(id);
	}

}
