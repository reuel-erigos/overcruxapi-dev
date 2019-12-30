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

import br.com.crux.cmd.AlterarDiagnosticosCmd;
import br.com.crux.cmd.CadastrarDiagnosticosCmd;
import br.com.crux.cmd.ExcluirDiagnosticosCmd;
import br.com.crux.cmd.GetDiagnosticosCmd;
import br.com.crux.to.DiagnosticosTO;

@RestController
@RequestMapping(value = "diagnosticos")
public class DiagnosticosService {
	
	@Autowired
	private GetDiagnosticosCmd getCmd;
	@Autowired
	private ExcluirDiagnosticosCmd  excluirCmd;
	@Autowired
	private AlterarDiagnosticosCmd alterarCmd;
	@Autowired
	private CadastrarDiagnosticosCmd cadastrarCmd;
	
	
	@GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<DiagnosticosTO> getAll() {
		return getCmd.getAll();
	}
	
	@GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public DiagnosticosTO getById(@PathVariable(name = "id") Long id) {
		return getCmd.getTOById(id);
	}
	
	@PostMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void cadastrar(@RequestBody DiagnosticosTO param) {
		cadastrarCmd.cadastrar(param);
	}
	
	@PutMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void alterar(@RequestBody DiagnosticosTO param) {
		alterarCmd.alterar(param);
	}
	
	@DeleteMapping(path = "/{id}")
	public void excluir(@PathVariable(name = "id") Long id) {
		excluirCmd.excluir(id);
	}

}
