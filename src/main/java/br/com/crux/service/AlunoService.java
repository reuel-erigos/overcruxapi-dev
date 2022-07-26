package br.com.crux.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.crux.cmd.AlterarAlunoCmd;
import br.com.crux.cmd.CadastrarAlunoCmd;
import br.com.crux.cmd.ExcluirAlunoCmd;
import br.com.crux.cmd.GetAlunoCmd;
import br.com.crux.to.AlunoTO;
import br.com.crux.to.ComboAlunoTO;
import br.com.crux.to.filtro.FiltroAlunoTO;
import br.com.crux.to.relatorios.beneficiarios.DadosObservacaoRelatorio;

@RestController
@RequestMapping(value = "aluno")
public class AlunoService {
	
	@Autowired
	private GetAlunoCmd getCmd;
	@Autowired
	private ExcluirAlunoCmd  excluirCmd;
	@Autowired
	private AlterarAlunoCmd alterarCmd;
	@Autowired
	private CadastrarAlunoCmd cadastrarCmd;
	
	
	@GetMapping(path = "/filter", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<AlunoTO> getAllFilter(@RequestParam(name = "idAluno", required = false) Long idAluno,
                                      @RequestParam(name = "nomePessoaFisicaMae", required = false) String nomePessoaFisicaMae,
                                      @RequestParam(name = "cpfPessoaFisicaAluno", required = false) String cpfPessoaFisicaAluno,
                                      @RequestParam(name = "dataInicioEntradaInstituicao", required = false) Long dataInicioEntradaInstituicao,
                                      @RequestParam(name = "dataFimEntradaInstituicao", required = false) Long dataFimEntradaInstituicao
                                                     ) {
		return getCmd.getAllFilter(idAluno, nomePessoaFisicaMae, cpfPessoaFisicaAluno, dataInicioEntradaInstituicao, dataFimEntradaInstituicao);
	}
	
	@PostMapping("/paged/filtro")
	public Page<AlunoTO> filterPagedPost(@RequestBody FiltroAlunoTO filtro,
			@RequestHeader("page") int page, @RequestHeader("pageSize") int pageSize) {

		final Pageable pageable = PageRequest.of(page, pageSize, Direction.ASC, "pessoasFisica.nome");
		final Page<AlunoTO> pageData = getCmd.listFilteredAndPaged(filtro, pageable);
		return pageData;
	}

	
	@GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<AlunoTO> getAll() {
		return getCmd.getAll();
	}
	
	@GetMapping(path = "/dados/combo", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<ComboAlunoTO> getAllAlunosByCombo() {
		return getCmd.getAllAlunosByCombo();
	}
	
	@GetMapping(path = "/nome/{nome}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<AlunoTO> getAlunosByNomel(@PathVariable(name = "nome") String nome) {
		return getCmd.getAlunosByNome(nome);
	}
	
	@GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public AlunoTO getById(@PathVariable(name = "id") Long id) {
		return getCmd.getTOById(id);
	}
	
	@PostMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE)
	@Transactional
	public AlunoTO cadastrar(@RequestBody AlunoTO param) {
		return cadastrarCmd.cadastrar(param);
	}
	
	@PutMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE)
	@Transactional
	public AlunoTO alterar(@RequestBody AlunoTO param) {
		return alterarCmd.alterar(param);
	}
	
	@DeleteMapping(path = "/{id}")
	public void excluir(@PathVariable(name = "id") Long id) {
		excluirCmd.excluir(id);
	}

	
	@Transactional
	@PutMapping(path = "/observacao", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void gerar(@RequestBody DadosObservacaoRelatorio  dadosObservacaoRelatorio) {
		alterarCmd.salvarTextoObservacaoRelatorio(dadosObservacaoRelatorio);
	}
}
