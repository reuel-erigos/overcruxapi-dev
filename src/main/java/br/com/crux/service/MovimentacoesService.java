package br.com.crux.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.crux.cmd.AlterarMovimentacoesCmd;
import br.com.crux.cmd.CadastrarMovimentacoesCmd;
import br.com.crux.cmd.ExcluirMovimentacoesCmd;
import br.com.crux.cmd.GetMovimentacoesCmd;
import br.com.crux.to.MovimentacoesTO;

@RestController
@RequestMapping("movimentacoes")
public class MovimentacoesService {

	@Autowired private GetMovimentacoesCmd getCmd;
	@Autowired private CadastrarMovimentacoesCmd cadastrarCmd;
	@Autowired private AlterarMovimentacoesCmd alterarCmd;
	@Autowired private ExcluirMovimentacoesCmd excluirCmd;

	
	@GetMapping(path = "/filter/origem", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<MovimentacoesTO> getAllFilter(@RequestParam(name = "empresa", required = false) Long idEmpresa,
                                              @RequestParam(name = "programa", required = false) Long idPrograma,
                                              @RequestParam(name = "projeto", required = false) Long idProjeto,
                                              @RequestParam(name = "valor", required = false) String valor,
                                              @RequestParam(name = "dataInicioDoc", required = false) Long dataInicioDoc,
                                              @RequestParam(name = "dataFimDoc", required = false) Long dataFimDoc,
                                              @RequestParam(name = "dataVencimento", required = false) Long dataVencimento,
                                              @RequestParam(name = "dataInicioMov", required = false) Long dataInicioMov,
                                              @RequestParam(name = "dataFimMov", required = false) Long dataFimMov,
                                              @RequestParam(name = "dataInicioPag", required = false) Long dataInicioPag,
                                              @RequestParam(name = "dataFimPag", required = false) Long dataFimPag,
                                              @RequestParam(name = "numeroDocumento", required = false) String numeroDocumento
                                                     ) {
		return getCmd.getAllFilter(idEmpresa, idPrograma, idProjeto, valor, 
				                   dataInicioDoc, dataFimDoc, dataVencimento, 
				                   dataInicioMov, dataFimMov, 
				                   dataInicioPag, dataFimPag, 
				                   numeroDocumento);
	}
	
	
	@GetMapping("/destino")
	public List<MovimentacoesTO> getAllDestino() {
		return getCmd.getAllDestino();
	}
	
	@GetMapping("/origem")
	public List<MovimentacoesTO> getAllOrigem() {
		return getCmd.getAllOrigem();
	}

	@GetMapping("/{id}")
	public MovimentacoesTO getById(@PathVariable Long id) {
		return getCmd.getTOById(id);
	}

	@PostMapping
	@Transactional
	public MovimentacoesTO cadastrar(@RequestBody MovimentacoesTO to) {
		return cadastrarCmd.cadastrar(to);
	}

	@PutMapping
	@Transactional
	public MovimentacoesTO alterar(@RequestBody MovimentacoesTO to) {
		return alterarCmd.alterar(to);
	}

	@DeleteMapping("/{id}")
	public void excluir(@PathVariable Long id) {
		excluirCmd.excluir(id);
	}

}
