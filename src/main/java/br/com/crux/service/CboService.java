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

import br.com.crux.cmd.AlterarCboCmd;
import br.com.crux.cmd.CadastrarCboCmd;
import br.com.crux.cmd.ExcluirCboCmd;
import br.com.crux.cmd.GetCboCmd;
import br.com.crux.to.CboTO;

@RestController
@RequestMapping(value = "cbo")
public class CboService {

	@Autowired private GetCboCmd getCmd;
	@Autowired private ExcluirCboCmd excluirCmd;
	@Autowired private AlterarCboCmd alterarCmd;
	@Autowired private CadastrarCboCmd cadastrarCmd;

	@GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<CboTO> getAll() {
		return getCmd.getAll();
	}

	@GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public CboTO getById(@PathVariable(name = "id") Long id) {
		return getCmd.getById(id);
	}

	@PostMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void cadastrar(@RequestBody CboTO to) {
		cadastrarCmd.cadastrar(to);
	}

	@PutMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void alterar(@RequestBody CboTO to) {
		alterarCmd.alterar(to);
	}

	@DeleteMapping(path = "/{id}")
	public void excluir(@PathVariable(name = "id") Long id) {
		excluirCmd.excluir(id);
	}

}
