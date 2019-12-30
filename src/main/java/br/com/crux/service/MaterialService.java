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

import br.com.crux.cmd.AlterarMaterialCmd;
import br.com.crux.cmd.CadastrarMaterialCmd;
import br.com.crux.cmd.ExcluirMaterialCmd;
import br.com.crux.cmd.GetMaterialCmd;
import br.com.crux.to.MaterialTO;

@RestController
@RequestMapping("materiais")
public class MaterialService {

	@Autowired
	private GetMaterialCmd getCmd;
	@Autowired
	private ExcluirMaterialCmd excluirCmd;
	@Autowired
	private AlterarMaterialCmd alterarCmd;
	@Autowired
	private CadastrarMaterialCmd cadastrarCmd;

	@GetMapping
	public List<MaterialTO> getAll() {
		return getCmd.getAll();
	}

	@GetMapping("/{id}")
	public MaterialTO getById(@PathVariable(name = "id") Long id) {
		return getCmd.getTOById(id);
	}

	@PostMapping
	public void cadastrar(@RequestBody MaterialTO param) {
		cadastrarCmd.cadastrar(param);
	}

	@PutMapping
	public void alterar(@RequestBody MaterialTO param) {
		alterarCmd.alterar(param);
	}

	@DeleteMapping("/{id}")
	public void excluir(@PathVariable(name = "id") Long id) {
		excluirCmd.excluir(id);
	}

}
