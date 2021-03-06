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

import br.com.crux.cmd.AlterarTalentosPFCmd;
import br.com.crux.cmd.CadastrarTalentosPFCmd;
import br.com.crux.cmd.ExcluirTalentosPFCmd;
import br.com.crux.cmd.GetTalentosPFCmd;
import br.com.crux.to.TalentosPfTO;

@RestController
@RequestMapping(value = "talentospf")
public class TalentosPFService {

	@Autowired private GetTalentosPFCmd getCmd;
	@Autowired private ExcluirTalentosPFCmd excluirCmd;
	@Autowired private AlterarTalentosPFCmd alterarCmd;
	@Autowired private CadastrarTalentosPFCmd cadastrarCmd;

	@GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<TalentosPfTO> getAllPorUnidadeLogada() {
		return getCmd.getAllPorUnidadeLogada();
	}

	@GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public TalentosPfTO getById(@PathVariable(name = "id") Long id) {
		return getCmd.getTOById(id);
	}

	@GetMapping(path = "/pessoa/{idPessoa}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<TalentosPfTO> getByIdPessoaFisica(@PathVariable(name = "idPessoa") Long id) {
		return getCmd.getByIdPessoaFisica(id);
	}

	@PostMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void cadastrar(@RequestBody TalentosPfTO param) {
		cadastrarCmd.cadastrar(param);
	}

	@PutMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void alterar(@RequestBody TalentosPfTO param) {
		alterarCmd.alterar(param);
	}

	@DeleteMapping(path = "/{id}")
	public void excluir(@PathVariable(name = "id") Long id) {
		excluirCmd.excluir(id);
	}

}
