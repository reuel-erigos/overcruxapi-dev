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

import br.com.crux.cmd.AlterarCadastroReservaAtividadeCmd;
import br.com.crux.cmd.CadastrarCadastroReservaAtividadeCmd;
import br.com.crux.cmd.ExcluirCadastroReservaAtividadeCmd;
import br.com.crux.cmd.GetCadastroReservaAtividadeCmd;
import br.com.crux.to.CadastroReservaAtividadeTO;

@RestController
@RequestMapping(value = "cadastroreservaatividade")
public class CadastroReservaAtividadeService {
	
	@Autowired
	private GetCadastroReservaAtividadeCmd getCmd;
	@Autowired
	private ExcluirCadastroReservaAtividadeCmd  excluirCmd;
	@Autowired
	private AlterarCadastroReservaAtividadeCmd alterarCmd;
	@Autowired
	private CadastrarCadastroReservaAtividadeCmd cadastrarCmd;
	
	
	@GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<CadastroReservaAtividadeTO> getAll() {
		return getCmd.getAll();
	}
	
	@GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public CadastroReservaAtividadeTO getById(@PathVariable(name = "id") Long id) {
		return getCmd.getById(id);
	}
	
	@GetMapping(path = "/atividade/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<CadastroReservaAtividadeTO> getPorAtividade(@PathVariable(name = "id") Long id) {
		return getCmd.getPorAtividade(id);
	}
	
	@PostMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void cadastrar(@RequestBody CadastroReservaAtividadeTO param) {
		cadastrarCmd.cadastrar(param);
	}
	
	@PutMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void alterar(@RequestBody CadastroReservaAtividadeTO param) {
		alterarCmd.alterar(param);
	}
	
	@DeleteMapping(path = "/{id}")
	public void excluir(@PathVariable(name = "id") Long id) {
		excluirCmd.excluir(id);
	}

}
