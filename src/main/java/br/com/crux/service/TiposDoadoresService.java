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

import br.com.crux.cmd.AlterarTiposDoadoresCmd;
import br.com.crux.cmd.CadastrarTiposDoadoresCmd;
import br.com.crux.cmd.ExcluirTiposDoadoresCmd;
import br.com.crux.cmd.GetTiposDoadoresCmd;
import br.com.crux.to.TiposDoadoresTO;

@RestController
@RequestMapping(value = "situacoesexalunos")
public class TiposDoadoresService {

	@Autowired private GetTiposDoadoresCmd getCmd;
	@Autowired private CadastrarTiposDoadoresCmd cadastrarCmd;
	@Autowired private AlterarTiposDoadoresCmd alterarCmd;
	@Autowired private ExcluirTiposDoadoresCmd excluirCmd;
	
	@GetMapping
	public List<TiposDoadoresTO> getAll() {
		return getCmd.getAllTO();
	}

	@GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public TiposDoadoresTO getById(@PathVariable(name = "id") Long id) {
		return getCmd.getTOById(id);
	}

	@PostMapping
	@Transactional
	public void cadastrar(@RequestBody TiposDoadoresTO to) {
		cadastrarCmd.cadastrar(to);
	}

	@PutMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE)
	@Transactional
	public void alterar(@RequestBody TiposDoadoresTO to) {
		alterarCmd.alterar(to);
	}

	@DeleteMapping(path = "/{id}")
	@Transactional
	public void excluir(@PathVariable(name = "id") Long id) {
		excluirCmd.excluir(id);
	}

}
