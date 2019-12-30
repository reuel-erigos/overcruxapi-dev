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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.crux.cmd.AlterarTurmasCmd;
import br.com.crux.cmd.CadastrarTurmasCmd;
import br.com.crux.cmd.ExcluirTurmasCmd;
import br.com.crux.cmd.GetTurmasCmd;
import br.com.crux.to.TurmasTO;

@RestController
@RequestMapping(value = "turmas")
public class TurmasService {

	@Autowired private GetTurmasCmd getCmd;
	@Autowired private ExcluirTurmasCmd excluirCmd;
	@Autowired private AlterarTurmasCmd alterarCmd;
	@Autowired private CadastrarTurmasCmd cadastrarCmd;

	@GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<TurmasTO> getAll() {
		return getCmd.getAllFilter(null, null, null);
	}
	
	@GetMapping(path = "/filter", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<TurmasTO> getAllFilter(@RequestParam(name = "programa", required = false) Long idPrograma,
                                       @RequestParam(name = "projeto", required = false) Long idProjeto,
                                       @RequestParam(name = "unidade", required = false) Long idUnidade
                                                ) {
		return getCmd.getAllFilter(idPrograma, idProjeto, idUnidade);
	}
	
	
	@GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public TurmasTO getById(@PathVariable(name = "id") Long id) {
		return getCmd.getTOById(id);
	}

	@PostMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE)
	@Transactional
	public void cadastrar(@RequestBody TurmasTO to) {
		cadastrarCmd.cadastrar(to);
	}

	@PutMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE)
	@Transactional
	public void alterar(@RequestBody TurmasTO to) {
		alterarCmd.alterar(to);
	}

	@DeleteMapping(path = "/{id}")
	public void excluir(@PathVariable(name = "id") Long id) {
		excluirCmd.excluir(id);
	}

}
