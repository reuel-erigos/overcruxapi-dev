package br.com.crux.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.crux.cmd.ArquivoCmd;

@RestController
@RequestMapping(value = "arquivounidade")
public class ArquivoUnidadeService {

	@Autowired
	private ArquivoCmd arquivoCmd;
	
	@PostMapping(path = "")
	public void gravar(@RequestParam(name = "file") MultipartFile file ) {
		arquivoCmd.salvar(file);
	}
	
	@PostMapping(path = "/unidade/{idUnidade}")
	public void gravarComIdUnidade(@PathVariable(name = "idUnidade") Long idUnidade ,@RequestParam(name = "file") MultipartFile file ) {
		arquivoCmd.salvarComIdUnidade(file, idUnidade);
	}
	
	
	@PutMapping(path = "/unidade/{idUnidade}")
	@Transactional
	public void alterarComIdUnidade(@PathVariable(name = "idUnidade") Long idUnidade, @RequestParam(name = "file") MultipartFile file) {
		arquivoCmd.alterarArquivoUnidade(file,idUnidade);
	}
	
	@PutMapping(path = "")
	public void alterar(@RequestParam(name = "file") MultipartFile file) {
		arquivoCmd.salvar(file);
	}
	
	@GetMapping(path = "/{idUnidade}")
	public byte[] getPorUnidade(@PathVariable(name = "idUnidade") Long idUnidade) {
		return arquivoCmd.getArquivoPorUnidade(idUnidade);
	}	
	
}
