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

import br.com.crux.cmd.AlterarInstituicaoCmd;
import br.com.crux.cmd.CadastrarInstituicaoCmd;
import br.com.crux.cmd.ExcluirInstituicaoCmd;
import br.com.crux.cmd.GetInstituicaoCmd;
import br.com.crux.to.InstituicaoTO;

@RestController
@RequestMapping(value = "instituicao")
public class InstituicaoService {

	@Autowired private GetInstituicaoCmd getCmd;
	@Autowired private CadastrarInstituicaoCmd cadastrarCmd;
	@Autowired private ExcluirInstituicaoCmd excluirCmd;
	@Autowired private AlterarInstituicaoCmd alterarCmd;

	@GetMapping
	public List<InstituicaoTO> getAll() {
		return getCmd.getAll();
	}

	@GetMapping("/usuariologado")
	public List<InstituicaoTO> getInstituicoesComAcesso() {
		return getCmd.getInstituicoesComAcesso();
	}

	@GetMapping("/{id}")
	public InstituicaoTO getById(@PathVariable Long id) {
		return getCmd.getTOById(id);
	}

	@PostMapping
	@Transactional
	public InstituicaoTO cadastrar(@RequestBody InstituicaoTO to) {
		return cadastrarCmd.cadastrar(to);
	}

	@PutMapping
	@Transactional
	public InstituicaoTO alterar(@RequestBody InstituicaoTO to) {
		return alterarCmd.alterar(to);
	}

	@DeleteMapping(path = "/{id}")
	public void excluir(@PathVariable Long id) {
		excluirCmd.excluir(id);
	}

}
