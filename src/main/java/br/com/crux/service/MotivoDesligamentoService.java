package br.com.crux.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.crux.cmd.AlterarMotivoDesligamentoCmd;
import br.com.crux.cmd.CadastrarMotivoDesligamentoCmd;
import br.com.crux.cmd.ExcluirMotivoDesligamentoCmd;
import br.com.crux.cmd.GetMotivoDesligamentoCmd;
import br.com.crux.to.MotivoDesligamentoTO;

@RestController
@RequestMapping(value = "motivosdesligamentos")
public class MotivoDesligamentoService {

	@Autowired private GetMotivoDesligamentoCmd getCmd;
	@Autowired private CadastrarMotivoDesligamentoCmd cadastrarCmd;
	@Autowired private AlterarMotivoDesligamentoCmd alterarCmd;
	@Autowired private ExcluirMotivoDesligamentoCmd excluirCmd;
	
	@GetMapping
	public List<MotivoDesligamentoTO> getAll() {
		return getCmd.getAll();
	}

	@GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public MotivoDesligamentoTO getById(@PathVariable(name = "id") Long id) {
		return getCmd.getTOById(id);
	}

	@PostMapping
	@Transactional
	public void cadastrar(@RequestBody MotivoDesligamentoTO to) {
		cadastrarCmd.cadastrar(to);
	}

	@PutMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE)
	@Transactional
	public void alterar(@RequestBody MotivoDesligamentoTO to) {
		alterarCmd.alterar(to);
	}

	@DeleteMapping(path = "/{id}")
	@Transactional
	public void excluir(@PathVariable(name = "id") Long id) {
		excluirCmd.excluir(id);
	}

}
