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

import br.com.crux.cmd.AlterarNiveisTurmasCmd;
import br.com.crux.cmd.CadastrarNiveisTurmasCmd;
import br.com.crux.cmd.ExcluirNiveisTurmasCmd;
import br.com.crux.cmd.GetNiveisTurmasCmd;
import br.com.crux.to.NiveisTurmasTO;

@RestController
@RequestMapping(value = "niveisturmas")
public class NiveisTurmasService {

	@Autowired private GetNiveisTurmasCmd getCmd;
	@Autowired private ExcluirNiveisTurmasCmd excluirCmd;
	@Autowired private AlterarNiveisTurmasCmd alterarCmd;
	@Autowired private CadastrarNiveisTurmasCmd cadastrarCmd;

	@GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<NiveisTurmasTO> getAll() {
		return getCmd.getAll();
	}

	@GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public NiveisTurmasTO getById(@PathVariable(name = "id") Long id) {
		return getCmd.getTOById(id);
	}

	@PostMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void cadastrar(@RequestBody NiveisTurmasTO to) {
		cadastrarCmd.cadastrar(to);
	}

	@PutMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void alterar(@RequestBody NiveisTurmasTO to) {
		alterarCmd.alterar(to);
	}

	@DeleteMapping(path = "/{id}")
	public void excluir(@PathVariable(name = "id") Long id) {
		excluirCmd.excluir(id);
	}

}
