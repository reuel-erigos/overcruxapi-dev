package br.com.crux.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.crux.cmd.relatorios.secretaria.GerarRelatorioBeneficiarioCmd;

@RestController
@RequestMapping(value = "relatoriobeneficiario")
public class RelatorioBeneficiarioService {

	@Autowired
	private GerarRelatorioBeneficiarioCmd gerarRelatorioBeneficiarioCmd;
	

	@PostMapping(path = "/mimetype/{mimetype}/tipo/{tiporelatorio}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public byte[] gerar(@RequestBody List<Integer> listaIdsPessoaFisica, 
			            @PathVariable(name = "mimetype") String mimeType, 
			            @PathVariable(name = "tiporelatorio") String tipoRelatorio) {
		return gerarRelatorioBeneficiarioCmd.gerar(listaIdsPessoaFisica, mimeType, tipoRelatorio);
	}
	
}
