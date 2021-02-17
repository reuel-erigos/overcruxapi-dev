package br.com.crux.service;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.crux.cmd.GetArquivoCmd;
import br.com.crux.cmd.GravarArquivoCmd;
import br.com.crux.to.ArquivoMetadadoTO;
import br.com.crux.to.ArquivoTO;

@RestController
@RequestMapping(value = "arquivos")
public class ArquivosService {

	@Autowired private GravarArquivoCmd gravarCmd;

	@Autowired private GetArquivoCmd getCmd;

	@Transactional
	@PostMapping(path = "")
	public ArquivoMetadadoTO gravarAnexos(@RequestParam(name = "file") MultipartFile file) {
		return gravarCmd.salvar(file);
	}

	@GetMapping(path = "/{id}")
	public byte[] get(@PathVariable(name = "id") Long id) {
		return getCmd.get(id);
	}


	@SuppressWarnings("rawtypes")
	@Transactional
	@RequestMapping(value = "/conteudo/{id}/nome/{nomeArquivo}", method = RequestMethod.GET)
	public HttpEntity getFile(HttpServletResponse response, 
			                  @PathVariable("id") Long idArquivoMetadados, 
			                  @PathVariable("nomeArquivo") String nomeArquivo) {
		ArquivoTO arquivoTO = getCmd.getDados(idArquivoMetadados);
		
		try (InputStream inputStream = new ByteArrayInputStream(arquivoTO.getBlob())) {
			StreamUtils.copy(inputStream, response.getOutputStream());
			response.setContentType(arquivoTO.getMetadados().getDsTipoArquivo());
			response.setHeader("filename", nomeArquivo);
		} catch (Exception ex) {
			
		}
		return new ResponseEntity(HttpStatus.OK);
	}
	
	
	@PostMapping(path = "/dados/{id}/nome/{nomeArquivo}",  produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public byte[] getArquivo(@PathVariable("id") Long idArquivoMetadados, @PathVariable("nomeArquivo") String nomeArquivo) {
		ArquivoTO arquivoTO = getCmd.getDados(idArquivoMetadados);
		return arquivoTO.getBlob();
	}

}
