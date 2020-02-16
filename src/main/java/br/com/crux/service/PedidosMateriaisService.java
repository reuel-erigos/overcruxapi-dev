package br.com.crux.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.crux.cmd.GetPedidosMateriaisCmd;
import br.com.crux.to.PedidosMateriaisTO;

@RestController
@RequestMapping(value = "pedidosmateriais")
public class PedidosMateriaisService {

	//TODO COMPLETAR O SERVICE QUEM FOR FAZER O CARTAO 

	@Autowired private GetPedidosMateriaisCmd getCmd;
	//	@Autowired private CadastrarCategoriasContabeisCmd cadastrarCmd;
	//	@Autowired private AlterarCategoriasContabeisCmd alterarCmd;
	//	@Autowired private ExcluirCategoriasContabeisCmd excluirCmd;

	//	@GetMapping
	//	public List<PedidosMateriaisTO> getAll() {
	//		return getCmd.getAll();
	//	}

	@GetMapping("/combo")
	public List<PedidosMateriaisTO> getAllCombo() {
		return getCmd.getAllCombo();
	}
	//
	//	@GetMapping("/{id}")
	//	public CategoriasContabeisTO getById(@PathVariable(name = "id") Long id) {
	//		return getCmd.getTOById(id);
	//	}
	//
	//	@PostMapping
	//	public void cadastrar(@RequestBody CategoriasContabeisTO to) {
	//		cadastrarCmd.cadastrar(to);
	//	}
	//
	//	@PutMapping
	//	public void alterar(@RequestBody CategoriasContabeisTO to) {
	//		alterarCmd.alterar(to);
	//	}
	//
	//	@DeleteMapping(path = "/{id}")
	//	public void excluir(@PathVariable(name = "id") Long id) {
	//		excluirCmd.excluir(id);
	//	}

}
