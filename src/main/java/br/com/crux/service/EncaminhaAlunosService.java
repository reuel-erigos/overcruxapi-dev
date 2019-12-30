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

import br.com.crux.cmd.AlterarEncaminhaAlunosCmd;
import br.com.crux.cmd.CadastrarEncaminhaAlunosCmd;
import br.com.crux.cmd.ExcluirEncaminhaAlunosCmd;
import br.com.crux.cmd.GetEncaminhaAlunosCmd;
import br.com.crux.to.EncaminhaAlunosTO;

@RestController
@RequestMapping(value = "encaminhaaluno")
public class EncaminhaAlunosService {
	
	@Autowired
	private GetEncaminhaAlunosCmd getCmd;
	@Autowired
	private ExcluirEncaminhaAlunosCmd  excluirCmd;
	@Autowired
	private AlterarEncaminhaAlunosCmd alterarCmd;
	@Autowired
	private CadastrarEncaminhaAlunosCmd cadastrarCmd;
	
	
	@GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<EncaminhaAlunosTO> getAll(@RequestParam(name = "aluno", required = false) Long idAluno,
                                          @RequestParam(name = "entidadesocial", required = false) Long idEntidadeSocial) {
		return getCmd.getAll(idAluno, idEntidadeSocial);
	}
	
	
	@GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public EncaminhaAlunosTO getById(@PathVariable(name = "id") Long id) {
		return getCmd.getById(id);
	}
	
	@PostMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void cadastrar(@RequestBody EncaminhaAlunosTO param) {
		cadastrarCmd.cadastrar(param);
	}
	
	@PutMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void alterar(@RequestBody EncaminhaAlunosTO param) {
		alterarCmd.alterar(param);
	}
	
	@DeleteMapping(path = "/{id}")
	public void excluir(@PathVariable(name = "id") Long id) {
		excluirCmd.excluir(id);
	}

}
