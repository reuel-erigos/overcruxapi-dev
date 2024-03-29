package br.com.crux.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.crux.cmd.GetMovimentacaoContabilCmd;
import br.com.crux.cmd.relatorios.financeiro.GerarRelatorioMovimentacaoContabilCmd;
import br.com.crux.to.relatorios.financeiro.MovimentacaoContabilTO;
import br.com.crux.to.relatorios.financeiro.SaldoContaContabilTO;

@RestController
@RequestMapping(value = "movimentacaocontabil")
public class RelatorioMovimentacaoContabilService {

	@Autowired private GerarRelatorioMovimentacaoContabilCmd gerarRelatorioMovimentacaoContabilCmd;
	@Autowired private GetMovimentacaoContabilCmd getCmd;
	
	
	@PostMapping(path = "/mimetype/{mimetype}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public byte[] gerar(@RequestBody List<MovimentacaoContabilTO> dados, 
			            @PathVariable(name = "mimetype") String mimeType) {
		return gerarRelatorioMovimentacaoContabilCmd.gerar(dados, mimeType);
	}
	
	@PutMapping(path = "/atualizarsaldocontacontabil/{idPlanoConta}/{dataInicio}")
	public void atualizarSaldoContaContabil(@PathVariable(name = "idPlanoConta") Long idPlanoConta,
			                                @PathVariable(name = "dataInicio") @DateTimeFormat(iso = ISO.DATE) LocalDate dataInicio
		                                   ) {
		getCmd.atualizarSaldoContaContabil(idPlanoConta, dataInicio);
	}	
	
	@PutMapping(path = "/atualizarsaldocontacontabilprograma/{idPlanoConta}/{dataInicio}/{idPrograma}")
	public void atualizarSaldoContaContabilPrograma(@PathVariable(name = "idPlanoConta") Long idPlanoConta,
			                                        @PathVariable(name = "dataInicio") @DateTimeFormat(iso = ISO.DATE) LocalDate dataInicio,
			                                        @PathVariable(name = "idPrograma") Long idPrograma
		                                            ) {
		getCmd.atualizarSaldoContaContabilPrograma(idPlanoConta, dataInicio, idPrograma);
	}	
	
	@PutMapping(path = "/atualizarsaldocontacontabilprojeto/{idPlanoConta}/{dataInicio}/{idProjeto}")
	public void atualizarSaldoContaContabilProjeto(@PathVariable(name = "idPlanoConta") Long idPlanoConta,
			                                       @PathVariable(name = "dataInicio") @DateTimeFormat(iso = ISO.DATE) LocalDate dataInicio,
			                                       @PathVariable(name = "idProjeto") Long idProjeto
		                                   ) {
		getCmd.atualizarSaldoContaContabilProjeto(idPlanoConta, dataInicio, idProjeto);
	}	
	
	
	
	@GetMapping(path = "/contascontabeissubordinadas/{idPlanoConta}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Integer> getContasContabeisSubordinadas(@PathVariable(name = "idPlanoConta", required = true) Long idPlanoConta) {
		return getCmd.getContasContabeisSubordinadas(idPlanoConta);
	}
	
	@GetMapping(path = "/saldocontacontabil", produces = MediaType.APPLICATION_JSON_VALUE)
	public SaldoContaContabilTO getSaldoContaContabil(@RequestParam(name = "idPlanoConta", required = true) Long idPlanoConta,
			                            @RequestParam(name = "dataInicio", required = true) @DateTimeFormat(iso = ISO.DATE) LocalDate dataInicio,
			                            @RequestParam(name = "dataFim", required = true) @DateTimeFormat(iso = ISO.DATE) LocalDate dataFim
		                               ) {
		return getCmd.getSaldoContaContabil(idPlanoConta, dataInicio, dataFim);
	}	

	@GetMapping(path = "/saldocontacontabilprograma", produces = MediaType.APPLICATION_JSON_VALUE)
	public SaldoContaContabilTO getSaldoContaContabilPrograma(@RequestParam(name = "idPlanoConta", required = true) Long idPlanoConta,
			                            @RequestParam(name = "dataInicio", required = true) @DateTimeFormat(iso = ISO.DATE) LocalDate dataInicio,
			                            @RequestParam(name = "dataFim", required = true) @DateTimeFormat(iso = ISO.DATE) LocalDate dataFim,
			                            @RequestParam(name = "idPrograma", required = true) Long idPrograma
		                               ) {
		return getCmd.getSaldoContaContabilPrograma(idPlanoConta, dataInicio, dataFim, idPrograma);
	}	

	@GetMapping(path = "/saldocontacontabilprojeto", produces = MediaType.APPLICATION_JSON_VALUE)
	public SaldoContaContabilTO getSaldoContaContabilProjeto(@RequestParam(name = "idPlanoConta", required = true) Long idPlanoConta,
			                            @RequestParam(name = "dataInicio", required = true) @DateTimeFormat(iso = ISO.DATE) LocalDate dataInicio,
			                            @RequestParam(name = "dataFim", required = true) @DateTimeFormat(iso = ISO.DATE) LocalDate dataFim,
			                            @RequestParam(name = "idProjeto", required = true) Long idProjeto
		                               ) {
		return getCmd.getSaldoContaContabilProjeto(idPlanoConta, dataInicio, dataFim, idProjeto);
	}	

	
	@GetMapping(path = "/filter", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<MovimentacaoContabilTO> getAllFilter(@RequestParam(name = "idcategoria", required = false) Long idcategoria,
		                                             @RequestParam(name = "idprograma", required = false) Long idPrograma,
		                                             @RequestParam(name = "idprojeto", required = false) Long idProjeto,
		                                             @RequestParam(name = "dataInicio", required = false) Long dataInicio,
		                                             @RequestParam(name = "dataFim", required = false) Long dataFim
		                                            ) {
		return getCmd.getAllFilter(idcategoria, idPrograma, idProjeto, dataInicio, dataFim);
	}	
}
