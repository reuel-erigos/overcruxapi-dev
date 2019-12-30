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

import br.com.crux.cmd.AlterarDocumentosAtividadeCmd;
import br.com.crux.cmd.CadastrarDocumentosAtividadeCmd;
import br.com.crux.cmd.ExcluirDocumentosAtividadeCmd;
import br.com.crux.cmd.GetDocumentosAtividadeCmd;
import br.com.crux.to.DocumentosAtividadeTO;

@RestController
@RequestMapping(value = "documentosatividade")
public class DocumentosAtividadeService {

	@Autowired private GetDocumentosAtividadeCmd getCmd;
	@Autowired private ExcluirDocumentosAtividadeCmd excluirCmd;
	@Autowired private AlterarDocumentosAtividadeCmd alterarCmd;
	@Autowired private CadastrarDocumentosAtividadeCmd cadastrarCmd;

	@GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<DocumentosAtividadeTO> getAll() {
		return getCmd.getAll();
	}

	@GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public DocumentosAtividadeTO getById(@PathVariable(name = "id") Long id) {
		return getCmd.getTOById(id);
	}

	@GetMapping(path = "/atividade/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<DocumentosAtividadeTO> getPorAtividade(@PathVariable(name = "id") Long id) {
		return getCmd.getPorAtividade(id);
	}

	@PostMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void cadastrar(@RequestBody DocumentosAtividadeTO param) {
		cadastrarCmd.cadastrar(param);
	}

	@PutMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void alterar(@RequestBody DocumentosAtividadeTO param) {
		alterarCmd.alterar(param);
	}

	@DeleteMapping(path = "/{id}")
	public void excluir(@PathVariable(name = "id") Long id) {
		excluirCmd.excluir(id);
	}

}
