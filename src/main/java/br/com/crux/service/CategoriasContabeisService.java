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

import br.com.crux.cmd.AlterarCategoriasContabeisCmd;
import br.com.crux.cmd.CadastrarCategoriasContabeisCmd;
import br.com.crux.cmd.ExcluirCategoriasContabeisCmd;
import br.com.crux.cmd.GetCategoriasContabeisCmd;
import br.com.crux.to.CategoriasContabeisTO;
import br.com.crux.to.PlanosContasTO;

@RestController
@RequestMapping(value = "planoscontascontabeis")
public class CategoriasContabeisService {

	@Autowired private GetCategoriasContabeisCmd getCmd;
	@Autowired private CadastrarCategoriasContabeisCmd cadastrarCmd;
	@Autowired private AlterarCategoriasContabeisCmd alterarCmd;
	@Autowired private ExcluirCategoriasContabeisCmd excluirCmd;

	@GetMapping
	public List<CategoriasContabeisTO> getAll() {
		return getCmd.getAllByInstituicaoLogada();
	}

	@GetMapping("/superior")
	public List<PlanosContasTO> getAllCategorias() {
		return getCmd.getAllByInstituicaoLogadaComboSuperior();
	}

	@GetMapping("/combo")
	public List<CategoriasContabeisTO> getAllCombo() {
		return getCmd.getAllCombo();
	}

	@GetMapping("/{id}")
	public CategoriasContabeisTO getById(@PathVariable(name = "id") Long id) {
		return getCmd.getTOById(id);
	}

	@PostMapping
	public void cadastrar(@RequestBody CategoriasContabeisTO to) {
		cadastrarCmd.cadastrar(to);
	}

	@PutMapping
	public void alterar(@RequestBody CategoriasContabeisTO to) {
		alterarCmd.alterar(to);
	}

	@DeleteMapping(path = "/{id}")
	public void excluir(@PathVariable(name = "id") Long id) {
		excluirCmd.excluir(id);
	}

}
