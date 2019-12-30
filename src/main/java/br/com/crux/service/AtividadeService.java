package br.com.crux.service;

import java.util.List;

import javax.transaction.Transactional;

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

import br.com.crux.cmd.AlterarAtividadeCmd;
import br.com.crux.cmd.CadastrarAtividadesCmd;
import br.com.crux.cmd.ExcluirAtividadeCmd;
import br.com.crux.cmd.GetAtividadeCmd;
import br.com.crux.to.AtividadesTO;

@RestController
@RequestMapping(value = "atividade")
public class AtividadeService {

	@Autowired private GetAtividadeCmd getCmd;
	@Autowired private ExcluirAtividadeCmd excluirCmd;
	@Autowired private AlterarAtividadeCmd alterarCmd;
	@Autowired private CadastrarAtividadesCmd cadastrarCmd;

	@GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<AtividadesTO> getAll() {
		return getCmd.getAllUnidadeLogada();
	}

	@GetMapping(path = "/vigente-e-passadas", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<AtividadesTO> getAllVigentesAndPassadas() {
		return getCmd.getAllVigentesAndPassadas();	
	}

	@GetMapping(path = "/vigente-e-futuras", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<AtividadesTO> getAllVigentesAndFuturas() {
		return getCmd.getAllVigentesAndFuturas();	
	}
	
	@GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public AtividadesTO getById(@PathVariable(name = "id") Long id) {
		return getCmd.getTOById(id);
	}

	@PostMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE)
	@Transactional
	public void cadastrar(@RequestBody AtividadesTO param) {
		cadastrarCmd.cadastrar(param);
	}

	@PutMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE)
	@Transactional
	public void alterar(@RequestBody AtividadesTO param) {
		alterarCmd.alterar(param);
	}

	@DeleteMapping(path = "/{id}")
	public void excluir(@PathVariable(name = "id") Long id) {
		excluirCmd.excluir(id);
	}

}
