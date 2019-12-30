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

import br.com.crux.cmd.AlterarAlunosTrabalhandoCmd;
import br.com.crux.cmd.CadastrarAlunosTrabalhandoCmd;
import br.com.crux.cmd.ExcluirAlunosTrabalhandoCmd;
import br.com.crux.cmd.GetAlunosTrabalhandoCmd;
import br.com.crux.to.AlunosTrabalhandoTO;

@RestController
@RequestMapping(value = "alunostrabalhando")
public class AlunosTrabalhandoService {

	@Autowired private GetAlunosTrabalhandoCmd getCmd;
	@Autowired private ExcluirAlunosTrabalhandoCmd excluirCmd;
	@Autowired private AlterarAlunosTrabalhandoCmd alterarCmd;
	@Autowired private CadastrarAlunosTrabalhandoCmd cadastrarCmd;

	@GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<AlunosTrabalhandoTO> getAll() {
		return getCmd.getAll();
	}

	@GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public AlunosTrabalhandoTO getById(@PathVariable(name = "id") Long id) {
		return getCmd.getById(id);
	}

	@PostMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void cadastrar(@RequestBody AlunosTrabalhandoTO param) {
		cadastrarCmd.cadastrar(param);
	}

	@PutMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void alterar(@RequestBody AlunosTrabalhandoTO param) {
		alterarCmd.alterar(param);
	}

	@DeleteMapping(path = "/{id}")
	public void excluir(@PathVariable(name = "id") Long id) {
		excluirCmd.excluir(id);
	}

}
