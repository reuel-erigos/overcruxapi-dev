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

import br.com.crux.cmd.AlterarFaltasFuncionarioCmd;
import br.com.crux.cmd.CadastrarFaltasFuncionarioCmd;
import br.com.crux.cmd.ExcluirFaltasFuncionarioCmd;
import br.com.crux.cmd.GetFaltasFuncionarioCmd;
import br.com.crux.to.FaltasFuncionarioTO;

@RestController
@RequestMapping(value = "faltasfuncionario")
public class FaltasFuncionarioService {
	
	@Autowired
	private GetFaltasFuncionarioCmd getCmd;
	@Autowired
	private ExcluirFaltasFuncionarioCmd excluirCmd;
	@Autowired
	private AlterarFaltasFuncionarioCmd alterarCmd;
	@Autowired
	private CadastrarFaltasFuncionarioCmd cadastrarCmd;
	
	
	@GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<FaltasFuncionarioTO> getAllPorUnidadeLogada() {
		return getCmd.getAllPorUnidadeLogada();
	}
	
	@GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public FaltasFuncionarioTO getById(@PathVariable(name = "id") Long id) {
		return getCmd.getTOById(id);
	}
	
	@GetMapping(path = "/funcionario/{idFuncionario}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<FaltasFuncionarioTO> getPorFuncionario(@PathVariable(name = "idFuncionario") Long idFuncionario) {
		return getCmd.getPorFuncionario(idFuncionario);
	}
	
	@PostMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void cadastrar(@RequestBody FaltasFuncionarioTO param) {
		cadastrarCmd.cadastrar(param);
	}
	
	@PutMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void alterar(@RequestBody FaltasFuncionarioTO param) {
		alterarCmd.alterar(param);
	}
	
	@DeleteMapping(path = "/{id}")
	public void excluir(@PathVariable(name = "id") Long id) {
		excluirCmd.excluir(id);
	}

}
