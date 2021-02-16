package br.com.crux.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.crux.cmd.GravarAnexosAcaoCmd;
import br.com.crux.to.ArquivoMetadadoTO;

@RestController
@RequestMapping(value = "gravararquivos")
public class GravarArquivosService {

	@Autowired
	private GravarAnexosAcaoCmd gravarAnexosAcaoCmd;

	
	@Transactional
	@PostMapping(path = "" )
	public ArquivoMetadadoTO gravarAnexos(@RequestParam(name = "file") MultipartFile file) {
		return gravarAnexosAcaoCmd.salvar(file);
	}
}
