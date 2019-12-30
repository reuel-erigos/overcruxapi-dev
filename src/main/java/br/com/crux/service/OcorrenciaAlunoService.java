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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.crux.cmd.AlterarOcorrenciaAlunoCmd;
import br.com.crux.cmd.CadastrarOcorrenciaAlunoCmd;
import br.com.crux.cmd.ExcluirOcorrenciaAlunoCmd;
import br.com.crux.cmd.GetOcorrenciaAlunoCmd;
import br.com.crux.to.OcorrenciaAlunoTO;

@RestController
@RequestMapping(value = "ocorrenciaaluno")
public class OcorrenciaAlunoService {

	@Autowired private GetOcorrenciaAlunoCmd getCmd;
	@Autowired private ExcluirOcorrenciaAlunoCmd excluirCmd;
	@Autowired private AlterarOcorrenciaAlunoCmd alterarCmd;
	@Autowired private CadastrarOcorrenciaAlunoCmd cadastrarCmd;

	@GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<OcorrenciaAlunoTO> getAll() {
		return getCmd.getAllFilter(null,  null);
	}	
	
	@GetMapping(path = "/filter", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<OcorrenciaAlunoTO> getAllFilter(@RequestParam(name = "tipoocorrencia", required = false) Long idTipoOcorrencia,
                                                @RequestParam(name = "aluno", required = false) Long idAluno) {
		return getCmd.getAllFilter(idTipoOcorrencia, idAluno);
	}	

	@GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public OcorrenciaAlunoTO getById(@PathVariable(name = "id") Long id) {
		return getCmd.getById(id);
	}

	@PostMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void cadastrar(@RequestBody OcorrenciaAlunoTO to) {
		cadastrarCmd.cadastrar(to);
	}

	@PutMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void alterar(@RequestBody OcorrenciaAlunoTO to) {
		alterarCmd.alterar(to);
	}

	@DeleteMapping(path = "/{id}")
	public void excluir(@PathVariable(name = "id") Long id) {
		excluirCmd.excluir(id);
	}

}
