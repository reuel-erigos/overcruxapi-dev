package br.com.crux.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.crux.cmd.GetEscolaCmd;
import br.com.crux.to.EscolaTO;

@RestController
@RequestMapping(value = "escola")
public class EscolaService {
	
	@Autowired
	private GetEscolaCmd getCmd;
	
	@GetMapping(path = "/dados/combo/{tipo}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<EscolaTO> getAllAlunosByCombo(@PathVariable(name = "tipo") String tipo) {
		return getCmd.getAllEscolasByCombo(tipo);
	}
	
}
