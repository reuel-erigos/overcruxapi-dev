package br.com.crux.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.crux.cmd.GetColaboradoresGestaoPessoalCmd;
import br.com.crux.cmd.relatorios.gestao_pessoal.GerarRelatorioGestaoPessoalCmd;
import br.com.crux.to.ColaboradoresGestaoPessoalTO;

@RestController
@RequestMapping(value = "relatoriogestaopessoal")
public class RelatorioGestaoPessoalService {

	@Autowired private GerarRelatorioGestaoPessoalCmd gerarCmd;
	@Autowired private GetColaboradoresGestaoPessoalCmd getCmd;
	

	@PostMapping(path = "/mimetype/{mimetype}/tipo/{tiporelatorio}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public byte[] gerar(@RequestBody List<Integer> listaIdsPessoaFisica, 
			            @PathVariable(name = "mimetype") String mimeType, 
			            @PathVariable(name = "tiporelatorio") String tipoRelatorio) {
		return gerarCmd.gerar(listaIdsPessoaFisica, mimeType, tipoRelatorio);
	}
	
	
	@GetMapping(path = "/filter", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<ColaboradoresGestaoPessoalTO> getAllFilter(@RequestParam(name = "cpf", required = false) String cpf,
		                                             @RequestParam(name = "idColaborador", required = false) Long idColaborador,
		                                             @RequestParam(name = "idUnidade", required = false) Long idUnidade,
		                                             @RequestParam(name = "idDepartamento", required = false) Long idDepartamento,
		                                             @RequestParam(name = "idCargo", required = false) Long idCargo,
		                                             @RequestParam(name = "idFuncao", required = false) Long idFuncao,
		                                             @RequestParam(name = "dataInicio", required = false) Long dataInicio,
		                                             @RequestParam(name = "dataFim", required = false) Long dataFim
		                                             ) {
		return getCmd.getAllFilter(cpf, idColaborador, idUnidade, idDepartamento, idCargo, idFuncao, dataInicio, dataFim);
	}
}
