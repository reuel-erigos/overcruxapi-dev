package br.com.crux.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.crux.cmd.GetProgramaProjetoInsticuicaoCmd;
import br.com.crux.to.ProgramaProjetoInstituicaoTO;

@RestController
@RequestMapping(value = "programasprojetos")
public class ProgramaProjetoInstituicaoService {

	@Autowired private GetProgramaProjetoInsticuicaoCmd getCmd;

	@GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<ProgramaProjetoInstituicaoTO> getAll(){
		return getCmd.getAll();
	}

}
