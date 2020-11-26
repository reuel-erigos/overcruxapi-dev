package br.com.crux.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.crux.cmd.relatorios.GerarFichaMatriculaCmd;
import br.com.crux.cmd.relatorios.ParametrosTO;

@RestController
@RequestMapping(value = "fichamatricula")
public class FichaMatriculaService {

	@Autowired
	private GerarFichaMatriculaCmd gerarFichaMatriculaCmd;
	

	@PostMapping(path = "/tipo/{tipo}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public byte[] gerarPDF(@RequestBody List<ParametrosTO> param, @PathVariable(name = "tipo") String tipo) {
		return gerarFichaMatriculaCmd.gerarPDF(param, tipo);
	}
	
}
