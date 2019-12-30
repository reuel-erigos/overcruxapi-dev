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

import br.com.crux.cmd.AlterarFamiliaresCmd;
import br.com.crux.cmd.CadastrarFamiliaresCmd;
import br.com.crux.cmd.ExcluirFamiliaresCmd;
import br.com.crux.cmd.GetFamiliaresCmd;
import br.com.crux.cmd.GetResponsavelFamiliarVigenteCmd;
import br.com.crux.to.FamiliaresTO;
import br.com.crux.to.ResponsaveisAlunoTO;

@RestController
@RequestMapping(value = "familiar")
public class FamiliaresService {
	
	@Autowired
	private GetFamiliaresCmd getCmd;
	@Autowired
	private ExcluirFamiliaresCmd  excluirCmd;
	@Autowired
	private AlterarFamiliaresCmd alterarCmd;
	@Autowired
	private CadastrarFamiliaresCmd cadastrarCmd;
	@Autowired
	private GetResponsavelFamiliarVigenteCmd getResponsavelFamiliarVigenteCmd;
	
	
	@GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<FamiliaresTO> getAll() {
		return getCmd.getAll();
	}

	@GetMapping(path = "/aluno/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<FamiliaresTO> getFamiliaresPorAluno(@PathVariable(name = "id") Long id) {
		return getCmd.getFamiliaresPorAluno(id);
	}
		
	@GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public FamiliaresTO getById(@PathVariable(name = "id") Long id) {
		return getCmd.getTOById(id);
	}

	@GetMapping(path = "/{idaluno}/responsavelvigente", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponsaveisAlunoTO getResponsavelVigente(@PathVariable(name = "idaluno") Long idAluno) {
		return getResponsavelFamiliarVigenteCmd.getResponsavelVigente(idAluno);
	}
	
	@PostMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void cadastrar(@RequestBody FamiliaresTO param) {
		cadastrarCmd.cadastrar(param);
	}
	
	@PutMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void alterar(@RequestBody FamiliaresTO param) {
		alterarCmd.alterar(param);
	}
	
	@DeleteMapping(path = "/{id}")
	public void excluir(@PathVariable(name = "id") Long id) {
		excluirCmd.excluir(id);
	}

}
