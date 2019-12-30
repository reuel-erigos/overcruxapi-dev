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

import br.com.crux.cmd.AlterarObjetivoCmd;
import br.com.crux.cmd.CadastrarObjetivoCmd;
import br.com.crux.cmd.ExcluirObjetivoCmd;
import br.com.crux.cmd.GetObjetivoCmd;
import br.com.crux.to.ObjetivoTO;

@RestController
@RequestMapping(value = "objetivo")
public class ObjetivoService {

	@Autowired private GetObjetivoCmd getObjetivoCmd;
	@Autowired private ExcluirObjetivoCmd excluirObjetivoCmd;
	@Autowired private AlterarObjetivoCmd alterarObjetivoCmd;
	@Autowired private CadastrarObjetivoCmd cadastrarObjetivoCmd;

	@GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<ObjetivoTO> getAll() {
		return getObjetivoCmd.getAll();
	}

	@GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ObjetivoTO getById(@PathVariable(name = "id") Long idDepartamento) {
		return getObjetivoCmd.getTOById(idDepartamento);
	}

	@PostMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void cadastrar(@RequestBody ObjetivoTO to) {
		cadastrarObjetivoCmd.cadastrar(to);
	}

	@PutMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void alterar(@RequestBody ObjetivoTO to) {
		alterarObjetivoCmd.alterar(to);
	}

	@DeleteMapping(path = "/{id}")
	public void excluir(@PathVariable(name = "id") Long id) {
		excluirObjetivoCmd.excluir(id);
	}

}
