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

import br.com.crux.cmd.AlterarSituacoesVulnerabilidadeCmd;
import br.com.crux.cmd.CadastrarSituacoesVulnerabilidadeCmd;
import br.com.crux.cmd.ExcluirSituacoesVulnerabilidadeCmd;
import br.com.crux.cmd.GetSituacoesVulnerabilidadeCmd;
import br.com.crux.to.SituacoesVulnerabilidadeTO;

@RestController
@RequestMapping(value = "situacoesvulnerabilidade")
public class SituacoesVulnerabilidadeService {
	
	@Autowired
	private GetSituacoesVulnerabilidadeCmd getCmd;
	@Autowired
	private ExcluirSituacoesVulnerabilidadeCmd  excluirCmd;
	@Autowired
	private AlterarSituacoesVulnerabilidadeCmd alterarCmd;
	@Autowired
	private CadastrarSituacoesVulnerabilidadeCmd cadastrarCmd;
	
	
	@GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<SituacoesVulnerabilidadeTO> getAll() {
		return getCmd.getAll();
	}
	
	@GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public SituacoesVulnerabilidadeTO getById(@PathVariable(name = "id") Long id) {
		return getCmd.getById(id);
	}
	
	@PostMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void cadastrar(@RequestBody SituacoesVulnerabilidadeTO param) {
		cadastrarCmd.cadastrar(param);
	}
	
	@PutMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void alterar(@RequestBody SituacoesVulnerabilidadeTO param) {
		alterarCmd.alterar(param);
	}
	
	@DeleteMapping(path = "/{id}")
	public void excluir(@PathVariable(name = "id") Long id) {
		excluirCmd.excluir(id);
	}

}
