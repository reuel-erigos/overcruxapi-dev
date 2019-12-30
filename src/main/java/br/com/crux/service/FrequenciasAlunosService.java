package br.com.crux.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.crux.cmd.AlterarFrequenciasAlunosCmd;
import br.com.crux.cmd.GetFrequenciasAlunosCmd;
import br.com.crux.to.FrequenciasAlunosTO;

@RestController
@RequestMapping(value = "frequenciaaluno")
public class FrequenciasAlunosService {

	@Autowired private GetFrequenciasAlunosCmd getCmd;
	@Autowired private AlterarFrequenciasAlunosCmd alterarCmd;
	
	
	@GetMapping(path = "/matriculado/atividade/{idAtividade}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<FrequenciasAlunosTO> getAlunosMatriculados(@PathVariable(name = "idAtividade") Long idAtividade,
			                                               @RequestParam(name = "datafrequencia") Long dataFrequencia) {
		return getCmd.getAllAlunosMatriculadosTO(idAtividade, dataFrequencia);
	}
	
	@GetMapping(path = "/frequencia/atividade/{idAtividade}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<FrequenciasAlunosTO> getListaFrequencia(@PathVariable(name = "idAtividade") Long idAtividade,
			                                            @RequestParam(name = "datafrequencia") Long dataFrequencia) {
		return getCmd.getListaFrequenciaTO(idAtividade, dataFrequencia);
	}
	
	@PutMapping(path = "/matriculado/atividade/{idAtividade}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void alterar(@PathVariable(name = "idAtividade") Long idAtividade,
			            @RequestParam(name = "datafrequencia") Long dataFrequencia,
			            @RequestBody List<FrequenciasAlunosTO> param) {
		alterarCmd.alterarAll(param, idAtividade, dataFrequencia);
	}	

}
