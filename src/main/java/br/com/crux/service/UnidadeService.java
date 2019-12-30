package br.com.crux.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.crux.cmd.AlterarUnidadeCmd;
import br.com.crux.cmd.CadastrarUnidadeCmd;
import br.com.crux.cmd.ExcluirUnidadeCmd;
import br.com.crux.cmd.GetUnidadeCmd;
import br.com.crux.cmd.GetUnidadeLogadaCmd;
import br.com.crux.enums.ClassificadorSituacaoImovel;
import br.com.crux.enums.TipoUnidade;
import br.com.crux.to.AcessoUnidadeTO;
import br.com.crux.to.UnidadeTO;

@RestController
@RequestMapping(value = "unidade")
public class UnidadeService {

	@Autowired
	private GetUnidadeCmd getUnidadeCmd;
	@Autowired
	private CadastrarUnidadeCmd cadastrarUnidadeCmd;
	@Autowired
	private ExcluirUnidadeCmd excluirUnidadeCmd;
	@Autowired
	private AlterarUnidadeCmd alterarUnidadeCmd;
	@Autowired
	private GetUnidadeLogadaCmd getUnidadeLogadaCmd;
	
	@GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<UnidadeTO> getAllUnidade() {
		return getUnidadeCmd.getAllUnidadesUsuarioLogadoTemAcesso();
	}
	
	
	@GetMapping(path = "/instituicao", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<UnidadeTO> getAllPorInstiuicao() {
		return getUnidadeCmd.getAllUnidadesByInstituicaoLogada();
	}
	
	
	@GetMapping(path = "/unidadelogada", produces = MediaType.APPLICATION_JSON_VALUE)
	public UnidadeTO getUnidadeLogada() {
		return getUnidadeLogadaCmd.getUnidadeTO();
	}
	
	@PostMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE)
	public UnidadeTO cadastrar(@RequestBody UnidadeTO unidade) {
		return cadastrarUnidadeCmd.cadastrar(unidade);
	}

	@PutMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE)
	public UnidadeTO alterar(@RequestBody UnidadeTO unidade) {
		return alterarUnidadeCmd.alterar(unidade);
	}
	
	@DeleteMapping(path = "/{unidade}")
	public void excluir(@PathVariable(name = "unidade") Long idUnidade) {
		excluirUnidadeCmd.excluir(idUnidade);
	}

	@GetMapping(path = "/tiposunidade", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<TipoUnidade> getAllTiposUnidade() {
		return getUnidadeCmd.getAllTiposUnidade();
	}

	@GetMapping(path = "/classificadorimovel", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<ClassificadorSituacaoImovel> getAllClassificadorSituacaoImovel() {
		return getUnidadeCmd.getAllClassificadorSituacaoImovel();
	}

	@GetMapping(path = "/usuario", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<AcessoUnidadeTO> getUnidadesComAcesso() {
		return getUnidadeCmd.getUnidadesComAcesso();
	}
	
	@GetMapping(path = "/logada/{idUnidade}", produces = MediaType.APPLICATION_JSON_VALUE)
	public UnidadeTO getUnidadeSetandoLogada(@PathVariable(name = "idUnidade") Long idUnidade) {
		return getUnidadeCmd.getUnidadeUsuarioLogadoComAcesso(idUnidade).get();
	}

	@GetMapping(path = "/{idUnidade}", produces = MediaType.APPLICATION_JSON_VALUE)
	public UnidadeTO getById(@PathVariable(name = "idUnidade") Long idUnidade) {
		return getUnidadeCmd.getTOById(idUnidade);
	}



}
