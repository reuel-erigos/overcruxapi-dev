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

import br.com.crux.cmd.AlterarCotacoesMaterialCmd;
import br.com.crux.cmd.CadastrarCotacoesMaterialCmd;
import br.com.crux.cmd.ExcluirCotacoesMaterialCmd;
import br.com.crux.cmd.GetCotacoesMaterialCmd;
import br.com.crux.to.CotacoesMaterialTO;

@RestController
@RequestMapping(value = "cotacoesmateriais")
public class CotacoesMaterialService {

	@Autowired private GetCotacoesMaterialCmd getCmd;
	@Autowired private CadastrarCotacoesMaterialCmd cadastrarCmd;
	@Autowired private AlterarCotacoesMaterialCmd alterarCmd;
	@Autowired private ExcluirCotacoesMaterialCmd excluirCmd;

	@GetMapping
	public List<CotacoesMaterialTO> getAll() {
		return getCmd.getAll();
	}

	@GetMapping("/material/{idMaterial}")
	public List<CotacoesMaterialTO> getPorMaterial(@PathVariable Long idMaterial) {
		return getCmd.getPorMaterial(idMaterial);
	}

	@GetMapping("/{id}")
	public CotacoesMaterialTO getById(@PathVariable Long id) {
		return getCmd.getTOById(id);
	}

	@PostMapping
	public void cadastrar(@RequestBody CotacoesMaterialTO to) {
		cadastrarCmd.cadastrar(to);
	}

	@PutMapping
	public void alterar(@RequestBody CotacoesMaterialTO to) {
		alterarCmd.alterar(to);
	}

	@DeleteMapping("/{id}")
	public void excluir(@PathVariable Long id) {
		excluirCmd.excluir(id);
	}

}
