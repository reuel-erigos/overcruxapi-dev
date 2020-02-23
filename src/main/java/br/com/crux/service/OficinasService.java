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

import br.com.crux.cmd.AlterarOficinasCmd;
import br.com.crux.cmd.CadastrarOficinasCmd;
import br.com.crux.cmd.ExcluirOficinasCmd;
import br.com.crux.cmd.GetOficinasCmd;
import br.com.crux.to.OficinasTO;

@RestController
@RequestMapping(value = "oficinas")
public class OficinasService {

	@Autowired private GetOficinasCmd getCmd;
	@Autowired private ExcluirOficinasCmd excluirCmd;
	@Autowired private AlterarOficinasCmd alterarCmd;
	@Autowired private CadastrarOficinasCmd cadastrarCmd;

	@GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<OficinasTO> getAll() {
		return getCmd.getAllUnidadeLogada();
	}

	@GetMapping(path = "/vigente-e-passadas", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<OficinasTO> getAllVigentesAndPassadas() {
		return getCmd.getAllVigentesAndPassadas();	
	}

	@GetMapping(path = "/vigente-e-futuras", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<OficinasTO> getAllVigentesAndFuturas() {
		return getCmd.getAllVigentesAndFuturas();	
	}
	
	@GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public OficinasTO getById(@PathVariable(name = "id") Long id) {
		return getCmd.getTOById(id);
	}

	@PostMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE)
	@Transactional
	public void cadastrar(@RequestBody OficinasTO param) {
		cadastrarCmd.cadastrar(param);
	}

	@PutMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE)
	@Transactional
	public void alterar(@RequestBody OficinasTO param) {
		alterarCmd.alterar(param);
	}

	@DeleteMapping(path = "/{id}")
	public void excluir(@PathVariable(name = "id") Long id) {
		excluirCmd.excluir(id);
	}

}
