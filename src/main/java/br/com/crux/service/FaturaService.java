package br.com.crux.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.crux.cmd.GetFaturaCmd;
import br.com.crux.to.FaturaTO;

@RestController
@RequestMapping(value = "faturas")
public class FaturaService {

	@Autowired private GetFaturaCmd getCmd;

	@GetMapping("/movimentacao/{id}")
	public List<FaturaTO> getAllPorMovimentacoes(@PathVariable Long id) {
		return getCmd.getAllPorMovimentacoes(id);
	}

}
