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

import br.com.crux.cmd.AlterarAlunosTurmaCmd;
import br.com.crux.cmd.CadastrarAlunosTurmaCmd;
import br.com.crux.cmd.ExcluirAlunosTurmaCmd;
import br.com.crux.cmd.GetAlunosTurmaCmd;
import br.com.crux.to.AlunosTurmaTO;

@RestController
@RequestMapping(value = "matriculas")
public class MatriculaAlunoService {

	@Autowired private GetAlunosTurmaCmd getCmd;
	@Autowired private ExcluirAlunosTurmaCmd excluirCmd;
	@Autowired private AlterarAlunosTurmaCmd alterarCmd;
	@Autowired private CadastrarAlunosTurmaCmd cadastrarCmd;


	@GetMapping(path = "/alunos", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<AlunosTurmaTO> getAllFilter(@RequestParam(name = "turma", required = false) Long idTurma,
			                                @RequestParam(name = "aluno", required = false) Long idAluno,
                                            @RequestParam(name = "oficina", required = false) Long idAtividade) {
		return getCmd.getAllFilter(idTurma, idAluno, idAtividade);
	}
	
	
	@GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<AlunosTurmaTO> getAll() {
		return getCmd.getAllFilter(null, null, null);
	}
	
	@GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public AlunosTurmaTO getById(@PathVariable(name = "id") Long id) {
		return getCmd.getTOById(id);
	}
	

	@PostMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void cadastrar(@RequestBody AlunosTurmaTO param) {
		cadastrarCmd.cadastrar(param);
	}

	@PutMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void alterar(@RequestBody AlunosTurmaTO param) {
		alterarCmd.alterar(param);
	}

	@DeleteMapping(path = "/{id}")
	public void excluir(@PathVariable(name = "id") Long id) {
		excluirCmd.excluir(id);
	}

}
