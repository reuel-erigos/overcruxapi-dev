package br.com.crux.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.crux.cmd.GerarArquivoExportacaoDadosAlunoCmd;
import br.com.crux.cmd.GetExportacaoDadosAlunoCmd;
import br.com.crux.to.AlunoTO;
import br.com.crux.to.ExportacaoDadosAlunoTO;
import br.com.crux.to.exportacao.ListaCompletaDadosExportar;
import br.com.crux.to.filtro.FiltroAlunoTO;
import br.com.crux.to.filtro.FiltroExportacaoTO;

@RestController
@RequestMapping("exportardadosaluno")
public class ExportacaoDadosAlunoService {

	@Autowired private GetExportacaoDadosAlunoCmd getCmd;
	@Autowired private GerarArquivoExportacaoDadosAlunoCmd gerarArquivoCmd;

	
	@GetMapping(path = "/filter", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<ExportacaoDadosAlunoTO> getAllFilter(@RequestParam(name = "cpf", required = false) String cpf,
		                                             @RequestParam(name = "idBeneficiario", required = false) Long idBeneficiario,
		                                             @RequestParam(name = "idMae", required = false) Long idMae,
		                                             @RequestParam(name = "idPai", required = false) Long idPai,
		                                             @RequestParam(name = "idPrograma", required = false) Long idPrograma,
		                                             @RequestParam(name = "idProjeto", required = false) Long idProjeto,
		                                             @RequestParam(name = "idUnidade", required = false) Long idUnidade,
		                                             @RequestParam(name = "idResponsavel", required = false) Long idResponsavel,
		                                             @RequestParam(name = "dataInicioEntradaInstituicao", required = false) Long dataInicioEntradaInstituicao,
		                                             @RequestParam(name = "dataFimEntradaInstituicao", required = false) Long dataFimEntradaInstituicao,
		                                             @RequestParam(name = "dataInicioVigenciaInstituicao", required = false) Long dataInicioVigenciaInstituicao,
		                                             @RequestParam(name = "dataFimVigenciaInstituicao", required = false) Long dataFimVigenciaInstituicao

		                                             ) {
		return getCmd.getAllFilter(cpf, idBeneficiario, idMae, idPai, idPrograma, idProjeto, idUnidade, idResponsavel, dataInicioEntradaInstituicao, dataFimEntradaInstituicao, dataInicioVigenciaInstituicao, dataFimVigenciaInstituicao);
	}
	
	@PostMapping("/filtro")
	public List<ExportacaoDadosAlunoTO> filterPost(@RequestBody FiltroExportacaoTO filtro) {
		return getCmd.getAllFilter(filtro);
	}
	
	
	@PostMapping(path = "/gerar-arquivo", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public byte[] gerar(@RequestBody ListaCompletaDadosExportar param) {
		return gerarArquivoCmd.gerar(param);
	}
	
}
