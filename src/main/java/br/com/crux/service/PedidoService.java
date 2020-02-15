package br.com.crux.service;

import java.util.Collections;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.crux.to.PedidosMateriaisTO;

@RestController
@RequestMapping(value = "pedidos")
public class PedidoService {

	//TODO QUANDO FOR FAZER O PEDIDOS

	//	@Autowired private GetCotacoesMaterialCmd getCmd;
	//	@Autowired private CadastrarCotacoesMaterialCmd cadastrarCmd;
	//	@Autowired private AlterarCotacoesMaterialCmd alterarCmd;
	//	@Autowired private ExcluirCotacoesMaterialCmd excluirCmd;

	@GetMapping
	public List<PedidosMateriaisTO> getAll() {
		//		return getCmd.getAll();
		return Collections.emptyList();
	}

	@GetMapping("/{id}")
	public PedidosMateriaisTO getById(@PathVariable Long id) {
		// return getCmd.getTOById(id);
		return null;
	}

	@PostMapping
	public void cadastrar(@RequestBody PedidosMateriaisTO to) {
		//		cadastrarCmd.cadastrar(to);
	}

	@PutMapping
	public void alterar(@RequestBody PedidosMateriaisTO to) {
		//		alterarCmd.alterar(to);
	}

	@DeleteMapping("/{id}")
	public void excluir(@PathVariable Long id) {
		//		excluirCmd.excluir(id);
	}

}
