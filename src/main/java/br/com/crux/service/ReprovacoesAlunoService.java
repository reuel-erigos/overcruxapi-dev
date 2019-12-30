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

import br.com.crux.cmd.AlterarReprovacoesAlunoCmd;
import br.com.crux.cmd.CadastrarReprovacoesAlunoCmd;
import br.com.crux.cmd.ExcluirReprovacoesAlunoCmd;
import br.com.crux.cmd.GetReprovacoesAlunoCmd;
import br.com.crux.to.ReprovacoesAlunoTO;

@RestController
@RequestMapping(value = "reprovacaoaluno")
public class ReprovacoesAlunoService {
	
	@Autowired
	private GetReprovacoesAlunoCmd getCmd;
	@Autowired
	private ExcluirReprovacoesAlunoCmd  excluirCmd;
	@Autowired
	private AlterarReprovacoesAlunoCmd alterarCmd;
	@Autowired
	private CadastrarReprovacoesAlunoCmd cadastrarCmd;
	
	
	@GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<ReprovacoesAlunoTO> getAll() {
		return getCmd.getAll();
	}
	
	@GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ReprovacoesAlunoTO getById(@PathVariable(name = "id") Long id) {
		return getCmd.getById(id);
	}
	
	@PostMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void cadastrar(@RequestBody ReprovacoesAlunoTO param) {
		cadastrarCmd.cadastrar(param);
	}
	
	@PutMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void alterar(@RequestBody ReprovacoesAlunoTO param) {
		alterarCmd.alterar(param);
	}
	
	@DeleteMapping(path = "/{id}")
	public void excluir(@PathVariable(name = "id") Long id) {
		excluirCmd.excluir(id);
	}

}
