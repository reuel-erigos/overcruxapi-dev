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

import br.com.crux.cmd.AlterarIniciativaCmd;
import br.com.crux.cmd.CadastrarIniciativaCmd;
import br.com.crux.cmd.ExcluirIniciativaCmd;
import br.com.crux.cmd.GetIniciativaCmd;
import br.com.crux.to.IniciativaTO;

@RestController
@RequestMapping(value = "iniciativa")
public class IniciativaService {

	@Autowired private GetIniciativaCmd getIniciativaCmd;
	@Autowired private ExcluirIniciativaCmd excluirIniciativaCmd;
	@Autowired private AlterarIniciativaCmd alterarIniciativaCmd;
	@Autowired private CadastrarIniciativaCmd cadastrarIniciativaCmd;

	@GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<IniciativaTO> getAll() {
		return getIniciativaCmd.getAll();
	}

	@GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public IniciativaTO getById(@PathVariable(name = "id") Long id) {
		return getIniciativaCmd.getTOById(id);
	}

	@PostMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void cadastrar(@RequestBody IniciativaTO to) {
		cadastrarIniciativaCmd.cadastrar(to);
	}

	@PutMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void alterar(@RequestBody IniciativaTO to) {
		alterarIniciativaCmd.alterar(to);
	}

	@DeleteMapping(path = "/{id}")
	public void excluir(@PathVariable(name = "id") Long id) {
		excluirIniciativaCmd.excluir(id);
	}

}
