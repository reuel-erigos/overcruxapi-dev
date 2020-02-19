package br.com.crux.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.crux.cmd.GetItensMovimentacoesCmd;
import br.com.crux.to.ItensMovimentacoesTO;

@RestController
@RequestMapping(value = "itensmovimentacoes")
public class ItensMovimentacoesService {

	@Autowired private GetItensMovimentacoesCmd getCmd;

	@GetMapping("/combo")
	public List<ItensMovimentacoesTO> getAllCombo() {
		return getCmd.getAllCombo();
	}

}
