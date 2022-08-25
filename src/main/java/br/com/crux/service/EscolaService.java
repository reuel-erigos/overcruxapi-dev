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

import br.com.crux.cmd.GetEscolaCmd;
import br.com.crux.to.EscolaTO;
import br.com.crux.to.EscolaTO;
import br.com.crux.to.filtro.FiltroEscolaTO;

@RestController
@RequestMapping(value = "escola")
public class EscolaService {
	
	@Autowired
	private GetEscolaCmd getCmd;
	
	@GetMapping(path = "/dados/combo/{tipo}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<EscolaTO> getAllAlunosByCombo(@PathVariable(name = "tipo") String tipo) {
		return getCmd.getAllEscolasByCombo(tipo);
	}
	
	@PostMapping("/paged/filtro")
	public Page<EscolaTO> filterPagedPost(@RequestBody FiltroEscolaTO filtro,
			@RequestHeader("page") int page, @RequestHeader("pageSize") int pageSize) {

		final Pageable pageable = PageRequest.of(page, pageSize, Direction.ASC, "nome");
		final Page<EscolaTO> pageData = getCmd.listFilteredAndPaged(filtro, pageable);
		return pageData;
	}

	@GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public EscolaTO getById(@PathVariable(name = "id") Long id) {
		return getCmd.getTOById(id);
	}
	
//	@PostMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE)
//	@Transactional
//	public void cadastrar(@RequestBody EscolaTO param) {
//		cadastrarCmd.cadastrar(param);
//	}
//	
//	@PutMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE)
//	@Transactional
//	public void alterar(@RequestBody EscolaTO param) {
//		alterarCmd.alterar(param);
//	}
//	
//	@DeleteMapping(path = "/{id}")
//	public void excluir(@PathVariable(name = "id") Long id) {
//		excluirCmd.excluir(id);
//	}
	
}
