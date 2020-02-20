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

import br.com.crux.cmd.AlterarPedidosMateriaisCmd;
import br.com.crux.cmd.CadastrarPedidosMateriaisCmd;
import br.com.crux.cmd.ExcluirPedidosMateriaisCmd;
import br.com.crux.cmd.GetPedidosMateriaisCmd;
import br.com.crux.to.PedidosMateriaisTO;

@RestController
@RequestMapping(value = "pedidosmateriais")
public class PedidosMateriaisService {

	@Autowired private GetPedidosMateriaisCmd getCmd;
	@Autowired private CadastrarPedidosMateriaisCmd cadastrarCmd;
	@Autowired private AlterarPedidosMateriaisCmd alterarCmd;
	@Autowired private ExcluirPedidosMateriaisCmd excluirCmd;

	@GetMapping
	public List<PedidosMateriaisTO> getAll() {
		return getCmd.getAll();
	}

	@GetMapping("/combo")
	public List<PedidosMateriaisTO> getAllCombo() {
		return getCmd.getAllCombo();
	}

	@GetMapping("/{id}")
	public PedidosMateriaisTO getById(@PathVariable Long id) {
		return getCmd.getTOById(id);
	}

	@PostMapping
	public void cadastrar(@RequestBody PedidosMateriaisTO to) {
		cadastrarCmd.cadastrar(to);
	}

	@PutMapping
	public void alterar(@RequestBody PedidosMateriaisTO to) {
		alterarCmd.alterar(to);
	}

	@DeleteMapping(path = "/{id}")
	public void excluir(@PathVariable(name = "id") Long id) {
		excluirCmd.excluir(id);
	}

}
