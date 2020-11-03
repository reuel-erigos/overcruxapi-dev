package br.com.crux.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.crux.cmd.GetExportacaoDadosAlunoCmd;
import br.com.crux.to.ExportacaoDadosAlunoTO;

@RestController
@RequestMapping("exportardadosaluno")
public class ExportacaoDadosAlunoService {

	@Autowired private GetExportacaoDadosAlunoCmd getCmd;

	
	@GetMapping(path = "/filter", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<ExportacaoDadosAlunoTO> getAllFilter(@RequestParam(name = "cpf", required = false) String cpf,
		                                             @RequestParam(name = "idBeneficiario", required = false) Long idBeneficiario,
		                                             @RequestParam(name = "idMae", required = false) Long idMae,
		                                             @RequestParam(name = "idPai", required = false) Long idPai,
		                                             @RequestParam(name = "idPrograma", required = false) Long idPrograma,
		                                             @RequestParam(name = "idProjeto", required = false) Long idProjeto,
		                                             @RequestParam(name = "idUnidade", required = false) Long idUnidade,
		                                             @RequestParam(name = "idResponsavel", required = false) Long idResponsavel,
		                                             @RequestParam(name = "dataInicioInstituicao", required = false) Long dataInicioInstituicao,
		                                             @RequestParam(name = "dataFimInstituicao", required = false) Long dataFimInstituicao
		                                             ) {
		return getCmd.getAllFilter(cpf, idBeneficiario, idMae, idPai, idPrograma, idProjeto, idUnidade, idResponsavel, dataInicioInstituicao, dataFimInstituicao);
	}
	
	
}
