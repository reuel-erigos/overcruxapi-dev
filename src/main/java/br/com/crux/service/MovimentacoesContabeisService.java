package br.com.crux.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.crux.cmd.AlterarMovimentacoesContabeisCmd;
import br.com.crux.cmd.CadastrarMovimentacoesContabeisCmd;
import br.com.crux.cmd.ExcluirMovimentacoesContabeisCmd;
import br.com.crux.cmd.GetMovimentacoesContabeisCmd;
import br.com.crux.to.MovimentacoesContabeisTO;

@RestController
@RequestMapping(value = "movimentacoescontabeis")
public class MovimentacoesContabeisService {

	@Autowired private GetMovimentacoesContabeisCmd getCmd;	
	@Autowired private CadastrarMovimentacoesContabeisCmd cadastrarCmd;	
	@Autowired private AlterarMovimentacoesContabeisCmd alterarCmd;	
	@Autowired private ExcluirMovimentacoesContabeisCmd excluirCmd;

	@GetMapping
	public List<MovimentacoesContabeisTO> getAll() {
		return getCmd.getAll();
	}
	
	@GetMapping("/{id}")
	public MovimentacoesContabeisTO getById(@PathVariable Long id) {
		return getCmd.getTOById(id);
	}

	@PostMapping
	public void cadastrar(@RequestBody MovimentacoesContabeisTO to) {
		cadastrarCmd.cadastrar(to);
	}

	@PutMapping
	public void alterar(@RequestBody MovimentacoesContabeisTO to) {
		alterarCmd.alterar(to);
	}

	@DeleteMapping("/{id}")
	public void excluir(@PathVariable Long id) {
		excluirCmd.excluir(id);
	}

}
