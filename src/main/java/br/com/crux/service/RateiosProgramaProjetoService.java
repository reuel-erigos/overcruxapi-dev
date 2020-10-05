package br.com.crux.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.crux.cmd.GetRateiosProgramaProjetoCmd;
import br.com.crux.to.RateiosProgramaProjetoTO;

@RestController
@RequestMapping(value = "rateiospp")
public class RateiosProgramaProjetoService {

	@Autowired private GetRateiosProgramaProjetoCmd getCmd;

	@GetMapping(path = "/movimentacao/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<RateiosProgramaProjetoTO> getAllPorIdMovimentacao(@PathVariable(name = "id") Long id){
		return getCmd.getAllPorIdMovimentacao(id);
	}

}
