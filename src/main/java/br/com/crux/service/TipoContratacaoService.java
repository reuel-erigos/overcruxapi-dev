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

import br.com.crux.cmd.AlterarTiposContratacoesCmd;
import br.com.crux.cmd.CadastrarTiposContratacoesCmd;
import br.com.crux.cmd.ExcluirTiposContratacoesCmd;
import br.com.crux.cmd.GetTiposContratacoesCmd;
import br.com.crux.to.TiposContratacoesTO;

@RestController
@RequestMapping("tiposcontratacoes")
public class TipoContratacaoService {

	@Autowired private GetTiposContratacoesCmd getCmd;
	@Autowired private CadastrarTiposContratacoesCmd cadastrarCmd;
	@Autowired private AlterarTiposContratacoesCmd alterarCmd;
	@Autowired private ExcluirTiposContratacoesCmd excluirCmd;

	@GetMapping
	public List<TiposContratacoesTO> getAll() {
		return getCmd.getAll();
	}

	@GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public TiposContratacoesTO getById(@PathVariable(name = "id") Long id) {
		return getCmd.getTOById(id);
	}

	@PostMapping
	@Transactional
	public void cadastrar(@RequestBody TiposContratacoesTO to) {
		cadastrarCmd.cadastrar(to);
	}

	@PutMapping
	@Transactional
	public void alterar(@RequestBody TiposContratacoesTO to) {
		alterarCmd.alterar(to);
	}

	@DeleteMapping(path = "/{id}")
	public void excluir(@PathVariable(name = "id") Long id) {
		excluirCmd.excluir(id);
	}

}
