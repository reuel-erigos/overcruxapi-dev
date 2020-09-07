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

import br.com.crux.cmd.AlterarDoadoresCmd;
import br.com.crux.cmd.CadastrarDoadoresCmd;
import br.com.crux.cmd.ExcluirDoadoresCmd;
import br.com.crux.cmd.GetDoadoresCmd;
import br.com.crux.to.DoadoresTO;

@RestController
@RequestMapping("doadores")
public class DoadoresService {

	@Autowired private GetDoadoresCmd getCmd;
	@Autowired private CadastrarDoadoresCmd cadastrarCmd;
	@Autowired private AlterarDoadoresCmd alterarCmd;
	@Autowired private ExcluirDoadoresCmd excluirCmd;

	@GetMapping("")
	public List<DoadoresTO> getAll() {
		return getCmd.getAllTO();
	}
	
	@GetMapping("/{id}")
	public DoadoresTO getById(@PathVariable Long id) {
		return getCmd.getTOById(id);
	}

	@PostMapping
	@Transactional
	public DoadoresTO cadastrar(@RequestBody DoadoresTO to) {
		return cadastrarCmd.cadastrar(to);
	}

	@PutMapping
	@Transactional
	public DoadoresTO alterar(@RequestBody DoadoresTO to) {
		return alterarCmd.alterar(to);
	}

	@DeleteMapping("/{id}")
	public void excluir(@PathVariable Long id) {
		excluirCmd.excluir(id);
	}

}
