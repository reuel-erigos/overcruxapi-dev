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

import br.com.crux.cmd.AlterarMovimentacoesMateriaisCmd;
import br.com.crux.cmd.CadastrarMovimentacoesMateriaisCmd;
import br.com.crux.cmd.ExcluirMovimentacoesMateriaisCmd;
import br.com.crux.cmd.GetMovimentacoesMateriaisCmd;
import br.com.crux.to.MovimentacoesMateriaisTO;

@RestController
@RequestMapping(value = "movimentacoesmateriais")
public class MovimentacoesMateriaisService {

	@Autowired private GetMovimentacoesMateriaisCmd getCmd;
	@Autowired private CadastrarMovimentacoesMateriaisCmd cadastrarCmd;
	@Autowired private AlterarMovimentacoesMateriaisCmd alterarCmd;
	@Autowired private ExcluirMovimentacoesMateriaisCmd excluirCmd;

	
	@GetMapping
	public List<MovimentacoesMateriaisTO> getAll() {
		return getCmd.getAll();
	}

	@GetMapping("/{id}")
	public MovimentacoesMateriaisTO getById(@PathVariable Long id) {
		return getCmd.getTOById(id);
	}

	@PostMapping
	public void cadastrar(@RequestBody MovimentacoesMateriaisTO to) {
		cadastrarCmd.cadastrar(to);
	}

	@PutMapping
	public void alterar(@RequestBody MovimentacoesMateriaisTO to) {
		alterarCmd.alterar(to);
	}

	@DeleteMapping("/{id}")
	public void excluir(@PathVariable Long id) {
		excluirCmd.excluir(id);
	}

}
