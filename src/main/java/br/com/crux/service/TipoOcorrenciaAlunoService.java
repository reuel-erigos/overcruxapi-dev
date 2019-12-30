package br.com.crux.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.crux.cmd.AlterarTipoOcorrenciaAlunoCmd;
import br.com.crux.cmd.CadastrarTipoOcorrenciaAlunoCmd;
import br.com.crux.cmd.ExcluirTipoOcorrenciaAlunoCmd;
import br.com.crux.cmd.GetTipoOcorrenciaAlunoCmd;
import br.com.crux.to.TipoOcorrenciaAlunoTO;

@RestController
@RequestMapping(value = "tipoocorrenciaaluno")
public class TipoOcorrenciaAlunoService {

	@Autowired private GetTipoOcorrenciaAlunoCmd getCmd;
	@Autowired private ExcluirTipoOcorrenciaAlunoCmd excluirCmd;
	@Autowired private AlterarTipoOcorrenciaAlunoCmd alterarCmd;
	@Autowired private CadastrarTipoOcorrenciaAlunoCmd cadastrarCmd;

	@GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<TipoOcorrenciaAlunoTO> getAll() {
		return getCmd.getAll();
	}

	@GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public TipoOcorrenciaAlunoTO getById(@PathVariable(name = "id") Long id) {
		return getCmd.getById(id);
	}

	@PostMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void cadastrar(@RequestBody TipoOcorrenciaAlunoTO to) {
		cadastrarCmd.cadastrar(to);
	}

	@PutMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void alterar(@RequestBody TipoOcorrenciaAlunoTO to) {
		alterarCmd.alterar(to);
	}

	@DeleteMapping(path = "/{id}")
	public void excluir(@PathVariable(name = "id") Long id) {
		excluirCmd.excluir(id);
	}

}
