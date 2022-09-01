package br.com.crux.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.crux.cmd.ArquivoCmd;
import br.com.crux.cmd.GetUnidadeLogadaCmd;
import br.com.crux.to.ArquivoMetadadoTO;
import br.com.crux.to.filtro.FiltroArquivoTO;

@RestController
@RequestMapping(value = "arquivoinstituicao")
public class ArquivoInstituicaoService {

	@Autowired
	private ArquivoCmd arquivoCmd;
	
	@Autowired 
	private GetUnidadeLogadaCmd getUnidadeLogadaCmd;


	@PostMapping("/paged/filtro")
	public Page<ArquivoMetadadoTO> filterPagedPost(@RequestBody FiltroArquivoTO filtro,
			@RequestHeader("page") int page, @RequestHeader("pageSize") int pageSize) {

		final Pageable pageable = PageRequest.of(page, pageSize, Direction.ASC, "nmArquivo");
		final Page<ArquivoMetadadoTO> pageData = arquivoCmd.listFilteredAndPaged(filtro, pageable);
		return pageData;
	}

	@PostMapping(path = "")
	public void gravar(@RequestParam(name = "file") MultipartFile file) {
		arquivoCmd.salvar(file);
	}

	@PostMapping(path = "/instituicao/{id}")
	public void gravarComIdInstituicao(@PathVariable(name = "id") Long id,
			@RequestParam(name = "file") MultipartFile file) {
		arquivoCmd.salvarComIdInstituicao(file, id);
	}

	@PutMapping(path = "/instituicao/{id}")
	@Transactional
	public void alterarComIdInstituicao(@PathVariable(name = "id") Long id,
			@RequestParam(name = "file") MultipartFile file) {
		arquivoCmd.alterarArquivoInstituicao(file, id);
	}

	@PutMapping(path = "")
	public void alterar(@RequestParam(name = "file") MultipartFile file) {
		arquivoCmd.salvar(file);
	}

	@GetMapping(path = "/{id}")
	public byte[] getPorUnidade(@PathVariable(name = "id") Long id) {
		return arquivoCmd.getArquivoPorInstituicao(id);
	}

	@PostMapping(path = "/instituicao/tipo/{tipo}")
	public void gravarComIdInstituicao(@PathVariable(name = "tipo") String tipo,
			@RequestParam(name = "file") MultipartFile file) {
		arquivoCmd.salvarComIdInstituicao(file, tipo);
	}


}
