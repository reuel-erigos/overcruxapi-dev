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
import org.springframework.web.bind.annotation.RestController;

import br.com.crux.cmd.AlterarRegiaoAdministrativaCmd;
import br.com.crux.cmd.CadastrarRegiaoAdministrativaCmd;
import br.com.crux.cmd.ExcluirRegiaoAdministrativaCmd;
import br.com.crux.cmd.GetRegiaoAdministrativaCmd;
import br.com.crux.to.RegiaoAdministrativaTO;
import br.com.crux.to.filtro.FiltroRegiaoAdministrativaTO;

@RestController
@RequestMapping(value = "regiaoadministrativa")
public class RegiaoAdministrativaService {
	
	@Autowired
	private GetRegiaoAdministrativaCmd getCmd;
	@Autowired
	private ExcluirRegiaoAdministrativaCmd  excluirCmd;
	@Autowired
	private AlterarRegiaoAdministrativaCmd alterarCmd;
	@Autowired
	private CadastrarRegiaoAdministrativaCmd cadastrarCmd;
	
	@PostMapping("/paged/filtro")
	public Page<RegiaoAdministrativaTO> filterPagedPost(@RequestBody FiltroRegiaoAdministrativaTO filtro,
			@RequestHeader("page") int page, @RequestHeader("pageSize") int pageSize) {

		final Pageable pageable = PageRequest.of(page, pageSize, Direction.ASC, "nome");
		final Page<RegiaoAdministrativaTO> pageData = getCmd.listFilteredAndPaged(filtro, pageable);
		return pageData;
	}

	
	@GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<RegiaoAdministrativaTO> getAll() {
		return getCmd.getAll();
	}
	
	@GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public RegiaoAdministrativaTO getById(@PathVariable(name = "id") Long id) {
		return getCmd.getTOById(id);
	}
	
	@PostMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE)
	@Transactional
	public void cadastrar(@RequestBody RegiaoAdministrativaTO param) {
		cadastrarCmd.cadastrar(param);
	}
	
	@PutMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE)
	@Transactional
	public void alterar(@RequestBody RegiaoAdministrativaTO param) {
		alterarCmd.alterar(param);
	}
	
	@DeleteMapping(path = "/{id}")
	public void excluir(@PathVariable(name = "id") Long id) {
		excluirCmd.excluir(id);
	}
	
	@GetMapping("/combo")
	public List<RegiaoAdministrativaTO> getAllCombo() {
		return getCmd.getAllCombo();
	}

}
