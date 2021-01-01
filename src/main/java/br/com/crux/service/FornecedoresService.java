package br.com.crux.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.crux.cmd.AlterarFornecedoresCmd;
import br.com.crux.cmd.CadastrarFornecedoresCmd;
import br.com.crux.cmd.ExcluirFornecedoresCmd;
import br.com.crux.cmd.GetFornecedoresCmd;
import br.com.crux.to.FornecedorTO;

@RestController
@RequestMapping(value = "fornecedores")
public class FornecedoresService {

	@Autowired private GetFornecedoresCmd getCmd;
	@Autowired private CadastrarFornecedoresCmd cadastrarCmd;
	@Autowired private AlterarFornecedoresCmd alterarCmd;
	@Autowired private ExcluirFornecedoresCmd excluirCmd;

	@GetMapping("")
	public List<FornecedorTO> getAll() {
		return getCmd.getAllTO();
	}
	
	@GetMapping("/{id}")
	public FornecedorTO getById(@PathVariable Long id) {
		return getCmd.getTOById(id);
	}
	
	@PostMapping
	@Transactional
	public FornecedorTO cadastrar(@RequestBody FornecedorTO to) {
		return cadastrarCmd.cadastrar(to);
	}

	@PutMapping
	@Transactional
	public FornecedorTO alterar(@RequestBody FornecedorTO to) {
		return alterarCmd.alterar(to);
	}

	@DeleteMapping("/{id}")
	public void excluir(@PathVariable Long id) {
		excluirCmd.excluir(id);
	}

}
