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

import br.com.crux.cmd.AlterarAcoesCompetenciaCmd;
import br.com.crux.cmd.CadastrarAcoesCompetenciaCmd;
import br.com.crux.cmd.ExcluirAcoesCompetenciaCmd;
import br.com.crux.cmd.GetAcoesCompetenciaCmd;
import br.com.crux.to.AcoesCompetenciaTO;

@RestController
@RequestMapping(value = "acoescompentencia")
public class AcoesCompetenciaService {

	@Autowired private GetAcoesCompetenciaCmd getCmd;
	@Autowired private ExcluirAcoesCompetenciaCmd excluirCmd;
	@Autowired private AlterarAcoesCompetenciaCmd alterarCmd;
	@Autowired private CadastrarAcoesCompetenciaCmd cadastrarCmd;

	@GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<AcoesCompetenciaTO> getAllPorUnidadeLogada() {
		return getCmd.getAllPorUnidadeLogada();
	}

	@GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public AcoesCompetenciaTO getById(@PathVariable(name = "id") Long id) {
		return getCmd.getTOById(id);
	}

	@GetMapping(path = "/pessoa/{idPessoa}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<AcoesCompetenciaTO> getPorPessoa(@PathVariable(name = "idPessoa") Long idPessoa) {
		return getCmd.getPorPessoa(idPessoa);
	}

	@PostMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void cadastrar(@RequestBody AcoesCompetenciaTO param) {
		cadastrarCmd.cadastrar(param);
	}

	@PutMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void alterar(@RequestBody AcoesCompetenciaTO param) {
		alterarCmd.alterar(param);
	}

	@DeleteMapping(path = "/{id}")
	public void excluir(@PathVariable(name = "id") Long id) {
		excluirCmd.excluir(id);
	}

}
