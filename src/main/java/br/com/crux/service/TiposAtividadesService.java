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

import br.com.crux.cmd.AlterarTiposAtividadesCmd;
import br.com.crux.cmd.CadastrarTiposAtividadesCmd;
import br.com.crux.cmd.ExcluirTiposAtividadesCmd;
import br.com.crux.cmd.GetTiposAtividadesCmd;
import br.com.crux.to.TiposAtividadesTO;

@RestController
@RequestMapping(value = "tiposatividades")
public class TiposAtividadesService {

	@Autowired private GetTiposAtividadesCmd getCmd;
	@Autowired private ExcluirTiposAtividadesCmd excluirCmd;
	@Autowired private AlterarTiposAtividadesCmd alterarCmd;
	@Autowired private CadastrarTiposAtividadesCmd cadastrarCmd;
	
	@GetMapping
	public List<TiposAtividadesTO> getAll() {
		return getCmd.getAll();
	}

	@GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public TiposAtividadesTO getById(@PathVariable(name = "id") Long id) {
		return getCmd.getTOById(id);
	}

	@PostMapping
	@Transactional
	public void cadastrar(@RequestBody TiposAtividadesTO to) {
		cadastrarCmd.cadastrar(to);
	}

	@PutMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE)
	@Transactional
	public void alterar(@RequestBody TiposAtividadesTO to) {
		alterarCmd.alterar(to);
	}

	@DeleteMapping(path = "/{id}")
	@Transactional
	public void excluir(@PathVariable(name = "id") Long id) {
		excluirCmd.excluir(id);
	}

}
