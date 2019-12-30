package br.com.crux.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.crux.cmd.AlterarAvaliacoesAlunosCmd;
import br.com.crux.cmd.ExcluirAvaliacoesAlunosCmd;
import br.com.crux.cmd.GetAvaliacoesAlunosCmd;
import br.com.crux.to.AvaliacoesAlunosTO;

@RestController
@RequestMapping(value = "avaliacaoaluno")
public class AvaliacoesAlunosService {

	@Autowired private GetAvaliacoesAlunosCmd getCmd;
	@Autowired private ExcluirAvaliacoesAlunosCmd excluirCmd;
	@Autowired private AlterarAvaliacoesAlunosCmd alterarCmd;

	
	@GetMapping(path = "/matriculado/atividade/{idAtividade}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<AvaliacoesAlunosTO> getAlunosMatriculados(@PathVariable(name = "idAtividade") Long idAtividade,
			                                               @RequestParam(name = "avaliacao") Long idAvaliacao) {
		return getCmd.getAllAlunosMatriculadosTO(idAtividade, idAvaliacao);
	}
	
	@GetMapping(path = "/avaliacao/atividade/{idAtividade}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<AvaliacoesAlunosTO> getListaFrequencia(@PathVariable(name = "idAtividade") Long idAtividade,
			                                            @RequestParam(name = "avaliacao") Long idAvaliacao) {
		return getCmd.getListaAvaliacoesTO(idAtividade, idAvaliacao);
	}
	
	@PutMapping(path = "/avaliacao/atividade/{idAtividade}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void alterarAll(@PathVariable(name = "idAtividade") Long idAtividade,
			               @RequestParam(name = "avaliacao") Long idAvaliacao,
			               @RequestBody List<AvaliacoesAlunosTO> param) {
		alterarCmd.alterarAll(param, idAtividade, idAvaliacao);
	}	
	
	@DeleteMapping(path = "/{id}")
	public void excluir(@PathVariable(name = "id") Long id) {
		excluirCmd.excluir(id);
	}	

}
