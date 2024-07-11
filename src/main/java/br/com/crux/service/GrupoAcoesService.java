package br.com.crux.service;

import javax.transaction.Transactional;

import br.com.crux.cmd.ExcluirGrupoAcoesCmd;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import br.com.crux.cmd.AlterarGrupoAcoesCmd;
import br.com.crux.cmd.CadastrarGrupoAcoesCmd;
import br.com.crux.cmd.GetGrupoAcoesCmd;
import br.com.crux.to.GrupoAcoesTO;

import java.util.List;

@RestController
@RequestMapping(value = "grupoacoes")
public class GrupoAcoesService {

	@Autowired private GetGrupoAcoesCmd getCmd;
	@Autowired private AlterarGrupoAcoesCmd alterarCmd;
	@Autowired private CadastrarGrupoAcoesCmd cadastrarCmd;
	@Autowired private ExcluirGrupoAcoesCmd excluirCmd;

	
	@GetMapping(path = "/numero/{numero}/atividade/{idAtividade}", produces = MediaType.APPLICATION_JSON_VALUE)
	public GrupoAcoesTO getByNumeroAndAtividade(@PathVariable(name = "numero") String numero,
			                                    @PathVariable(name = "idAtividade") Long idAtividade) {
		return getCmd.getByNumeroAndAtividade(numero,idAtividade);
	}

	@GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public GrupoAcoesTO getById(@PathVariable(name = "id") Long id) {
		return getCmd.getByTOId(id);
	}

	@Transactional
	@PostMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE)
	public GrupoAcoesTO cadastrar(@RequestBody GrupoAcoesTO param) {
		return cadastrarCmd.cadastrar(param);
	}

	@Transactional
	@PutMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE)
	public GrupoAcoesTO alterar(@RequestBody GrupoAcoesTO param) {
		return alterarCmd.alterar(param);
	}

	@GetMapping(path = "/filter", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<GrupoAcoesTO> findByFilters(@RequestParam(name = "unidade", required = false) Long idUnidade,
			@RequestParam(name = "turma", required = false) Long idTurma,
			@RequestParam(name = "oficina", required = false) Long idOficina,
			@RequestParam(name = "acao", required = false) Long idAcao,
			@RequestParam(name = "statusAnalise", required = false) String statusAnalise) {
		return getCmd.findByFilters(idUnidade, idTurma, idOficina, idAcao, statusAnalise);
	}

	@Transactional
	@DeleteMapping(path = "/{id}")
	public void excluir(@PathVariable(name = "id") Long id) {
		excluirCmd.excluir(id);
	}

}
