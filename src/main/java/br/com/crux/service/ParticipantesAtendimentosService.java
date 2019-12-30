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

import br.com.crux.cmd.AlterarParticipantesAtendimentosCmd;
import br.com.crux.cmd.CadastrarParticipantesAtendimentosCmd;
import br.com.crux.cmd.ExcluirParticipantesAtendimentosCmd;
import br.com.crux.cmd.GetParticipantesAtendimentosCmd;
import br.com.crux.to.ParticipantesAtendimentosTO;

@RestController
@RequestMapping(value = "participanteatendimento")
public class ParticipantesAtendimentosService {

	@Autowired private GetParticipantesAtendimentosCmd getCmd;
	@Autowired private ExcluirParticipantesAtendimentosCmd excluirCmd;
	@Autowired private AlterarParticipantesAtendimentosCmd alterarCmd;
	@Autowired private CadastrarParticipantesAtendimentosCmd cadastrarCmd;

	@GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<ParticipantesAtendimentosTO> getAll() {
		return getCmd.getAll();
	}

	@GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ParticipantesAtendimentosTO getById(@PathVariable(name = "id") Long id) {
		return getCmd.getById(id);
	}

	@PostMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void cadastrar(@RequestBody ParticipantesAtendimentosTO param) {
		cadastrarCmd.cadastrar(param);
	}

	@PutMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void alterar(@RequestBody ParticipantesAtendimentosTO param) {
		alterarCmd.alterar(param);
	}

	@DeleteMapping(path = "/{id}")
	public void excluir(@PathVariable(name = "id") Long id) {
		excluirCmd.excluir(id);
	}

}
