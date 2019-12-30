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

import br.com.crux.cmd.AlterarAcaoCmd;
import br.com.crux.cmd.CadastrarAcaoCmd;
import br.com.crux.cmd.ExcluirAcaoCmd;
import br.com.crux.cmd.GetAcaoCmd;
import br.com.crux.to.AcaoTO;

@RestController
@RequestMapping(value = "acao")
public class AcaoService {

	@Autowired private GetAcaoCmd getCmd;
	@Autowired private ExcluirAcaoCmd excluirCmd;
	@Autowired private AlterarAcaoCmd alterarCmd;
	@Autowired private CadastrarAcaoCmd cadastrarCmd;

	@GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<AcaoTO> getAll() {
		return getCmd.getAll();
	}

	@GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public AcaoTO getById(@PathVariable(name = "id") Long id) {
		return getCmd.getByTOId(id);
	}

	@PostMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void cadastrar(@RequestBody AcaoTO param) {
		cadastrarCmd.cadastrar(param);
	}

	@PutMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void alterar(@RequestBody AcaoTO param) {
		alterarCmd.alterar(param);
	}

	@DeleteMapping(path = "/{id}")
	public void excluir(@PathVariable(name = "id") Long id) {
		excluirCmd.excluir(id);
	}

}
