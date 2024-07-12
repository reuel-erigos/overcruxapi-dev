package br.com.crux.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.crux.cmd.ExcluirAcaoCmd;
import br.com.crux.cmd.GetAcaoCmd;
import br.com.crux.to.AcaoTO;

@RestController
@RequestMapping(value = "acoesoficinas")
public class AcaoService {

	@Autowired private GetAcaoCmd getCmd;
	@Autowired private ExcluirAcaoCmd excluirCmd;

	
	@GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<AcaoTO> getAllByInstituicao() {
		return getCmd.getAllByInstituicao();
	}
	
	@GetMapping(path = "/filter", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<AcaoTO> getAllFilter(@RequestParam(name = "unidade", required = false) Long idUnidade,
                                     @RequestParam(name = "turma", required = false) Long idTurma,
                                     @RequestParam(name = "oficina", required = false) Long idOficina,
                                     @RequestParam(name = "acao", required = false) Long idAcao,
									 @RequestParam(name = "statusAnalise", required = false) String statusAnalise) {
		return getCmd.getAllFilter(idUnidade, idTurma, idOficina, idAcao, statusAnalise);
	}
	

	@GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public AcaoTO getById(@PathVariable(name = "id") Long id) {
		return getCmd.getByTOId(id);
	}

	@DeleteMapping(path = "/{id}")
	public void excluir(@PathVariable(name = "id") Long id) {
		excluirCmd.excluir(id);
	}

}
