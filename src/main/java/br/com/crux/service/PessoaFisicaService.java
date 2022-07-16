package br.com.crux.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.crux.cmd.GetPessoaFisicaCmd;
import br.com.crux.to.ComboPessoaFisicaTO;
import br.com.crux.to.FornecedorColaboradorTO;
import br.com.crux.to.PessoaFisicaTO;

@RestController
@RequestMapping(value = "pessoafisica")
public class PessoaFisicaService {
	
	@Autowired
	private GetPessoaFisicaCmd getCmd;
	
	
	@GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<PessoaFisicaTO> getAll() {
		return getCmd.getAll();
	}
	
	@GetMapping(path = "/dados/combo", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<ComboPessoaFisicaTO> getAllByCombo() {
		return getCmd.getAllByCombo();
	}
	
	@GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public PessoaFisicaTO getById(@PathVariable(name = "id") Long id) {
		return getCmd.getTOById(id);
	}

	
	@GetMapping(path = "/fornecedor_colaborador", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<FornecedorColaboradorTO> getAllFornecedoresColaboradores() {
		return getCmd.getAllFornecedoresColaboradores();
	}
	
	@GetMapping(path = "/cpf", produces = MediaType.APPLICATION_JSON_VALUE)
	public PessoaFisicaTO getByCpf(@RequestParam(name = "cpf") String cpf) {
		return getCmd.getTOByCpf(cpf);
	}
}
