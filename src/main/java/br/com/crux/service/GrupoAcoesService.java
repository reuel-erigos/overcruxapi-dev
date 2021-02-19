package br.com.crux.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.crux.cmd.AlterarGrupoAcoesCmd;
import br.com.crux.cmd.CadastrarGrupoAcoesCmd;
import br.com.crux.cmd.GetGrupoAcoesCmd;
import br.com.crux.to.GrupoAcoesTO;

@RestController
@RequestMapping(value = "grupoacoes")
public class GrupoAcoesService {

	@Autowired private GetGrupoAcoesCmd getCmd;
	@Autowired private AlterarGrupoAcoesCmd alterarCmd;
	@Autowired private CadastrarGrupoAcoesCmd cadastrarCmd;

	
	@GetMapping(path = "/numero/{numero}", produces = MediaType.APPLICATION_JSON_VALUE)
	public GrupoAcoesTO getByNumero(@PathVariable(name = "numero") String numero) {
		return getCmd.getByNumero(numero);
	}

	@GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public GrupoAcoesTO getById(@PathVariable(name = "id") Long id) {
		return getCmd.getByTOId(id);
	}

	@PostMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE)
	public GrupoAcoesTO cadastrar(@RequestBody GrupoAcoesTO param) {
		return cadastrarCmd.cadastrar(param);
	}

	@PutMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE)
	public GrupoAcoesTO alterar(@RequestBody GrupoAcoesTO param) {
		return alterarCmd.alterar(param);
	}



}
