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

import br.com.crux.cmd.AlterarGrausInstrucaoCmd;
import br.com.crux.cmd.CadastrarGrausInstrucaoCmd;
import br.com.crux.cmd.ExcluirGrausInstrucaoCmd;
import br.com.crux.cmd.GetGrausInstrucaoCmd;
import br.com.crux.to.GrausInstrucaoTO;

@RestController
@RequestMapping(value = "grausinstrucao")
public class GrausInstrucaoService {
	
	@Autowired
	private GetGrausInstrucaoCmd getCmd;
	@Autowired
	private ExcluirGrausInstrucaoCmd  excluirCmd;
	@Autowired
	private AlterarGrausInstrucaoCmd alterarCmd;
	@Autowired
	private CadastrarGrausInstrucaoCmd cadastrarCmd;
	
	
	@GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<GrausInstrucaoTO> getAll() {
		return getCmd.getAll();
	}
	
	@GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public GrausInstrucaoTO getById(@PathVariable(name = "id") Long id) {
		return getCmd.getTOById(id);
	}
	
	@PostMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void cadastrar(@RequestBody GrausInstrucaoTO param) {
		cadastrarCmd.cadastrar(param);
	}
	
	@PutMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void alterar(@RequestBody GrausInstrucaoTO param) {
		alterarCmd.alterar(param);
	}
	
	@DeleteMapping(path = "/{id}")
	public void excluir(@PathVariable(name = "id") Long id) {
		excluirCmd.excluir(id);
	}
	
	
	
	
	

}
