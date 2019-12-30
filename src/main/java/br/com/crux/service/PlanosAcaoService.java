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

import br.com.crux.cmd.AlterarPlanosAcaoCmd;
import br.com.crux.cmd.CadastrarPlanosAcaoCmd;
import br.com.crux.cmd.ExcluirPlanosAcaoCmd;
import br.com.crux.cmd.GetPlanosAcaoCmd;
import br.com.crux.to.PlanosAcaoTO;

@RestController
@RequestMapping(value = "planosacao")
public class PlanosAcaoService {

	@Autowired private GetPlanosAcaoCmd getPlanosAcaoCmd;
	@Autowired private ExcluirPlanosAcaoCmd excluirPlanosAcaoCmd;
	@Autowired private AlterarPlanosAcaoCmd alterarPlanosAcaoCmd;
	@Autowired private CadastrarPlanosAcaoCmd cadastrarPlanosAcaoCmd;

	@GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<PlanosAcaoTO> getAll() {
		return getPlanosAcaoCmd.getAll();
	}

	@GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public PlanosAcaoTO getById(@PathVariable(name = "id") Long id) {
		return getPlanosAcaoCmd.getTOById(id);
	}

	@PostMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void cadastrar(@RequestBody PlanosAcaoTO to) {
		cadastrarPlanosAcaoCmd.cadastrar(to);
	}

	@PutMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void alterar(@RequestBody PlanosAcaoTO to) {
		alterarPlanosAcaoCmd.alterar(to);
	}

	@DeleteMapping(path = "/{id}")
	public void excluir(@PathVariable(name = "id") Long id) {
		excluirPlanosAcaoCmd.excluir(id);
	}

}
