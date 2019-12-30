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

import br.com.crux.cmd.AlterarProjetoCmd;
import br.com.crux.cmd.CadastrarProjetoCmd;
import br.com.crux.cmd.ExcluirProjetoCmd;
import br.com.crux.cmd.GetProjetoCmd;
import br.com.crux.to.ProjetoTO;

@RestController
@RequestMapping(value = "projeto")
public class ProjetoService {

	@Autowired private GetProjetoCmd getCmd;
	@Autowired private ExcluirProjetoCmd excluirCmd;
	@Autowired private AlterarProjetoCmd alterarCmd;
	@Autowired private CadastrarProjetoCmd cadastrarCmd;
	
	
	@GetMapping(path = "/instituicao/logada", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<ProjetoTO> getAllIntituicaoLogada() {
		return getCmd.getAllIntituicaoLogada();
	}
	
	@GetMapping(path = "/programa/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<ProjetoTO> getAllPorPrograma(@PathVariable(name = "id") Long id) {
		return getCmd.getAllPrograma(id);
	}	

	@GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<ProjetoTO> getAll() {
		return getCmd.getAll();
	}

	@GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ProjetoTO getById(@PathVariable(name = "id") Long id) {
		return getCmd.getTOById(id);
	}

	@PostMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE)
	@Transactional
	public void cadastrar(@RequestBody ProjetoTO to) {
		cadastrarCmd.cadastrar(to);
	}

	@PutMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void alterar(@RequestBody ProjetoTO to) {
		alterarCmd.alterar(to);
	}

	@DeleteMapping(path = "/{id}")
	public void excluir(@PathVariable(name = "id") Long id) {
		excluirCmd.excluir(id);
	}

}
