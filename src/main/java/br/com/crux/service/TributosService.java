package br.com.crux.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.crux.cmd.GetTributoCmd;
import br.com.crux.to.TributosTO;

@RestController
@RequestMapping("tributos")
public class TributosService {

	@Autowired private GetTributoCmd getCmd;

	@GetMapping("")
	public List<TributosTO> getAll() {
		return getCmd.getAllIntituicaoLogada();
	}

	@GetMapping("/{id}")
	public TributosTO getById(@PathVariable Long id) {
		return getCmd.getTOById(id);
	}


}
