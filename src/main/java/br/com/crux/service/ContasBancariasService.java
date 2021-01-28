package br.com.crux.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.crux.cmd.AlterarContasBancariaCmd;
import br.com.crux.cmd.CadastrarContasBancariaCmd;
import br.com.crux.cmd.ExcluirContasBancariaCmd;
import br.com.crux.cmd.GetContasBancariaCmd;
import br.com.crux.to.ContasBancariaTO;

@RestController
@RequestMapping(value = "contasbancarias")
public class ContasBancariasService {

	@Autowired private GetContasBancariaCmd getCmd;
	@Autowired private CadastrarContasBancariaCmd cadastrarCmd;
	@Autowired private AlterarContasBancariaCmd alterarCmd;
	@Autowired private ExcluirContasBancariaCmd excluirCmd;

	@GetMapping
	public List<ContasBancariaTO> getAll() {
		return getCmd.getAll();
	}

	@GetMapping("/combo")
	public List<ContasBancariaTO> getAllComboByUnidadeLogada() {
		return getCmd.getAllComboByUnidadeLogada();
	}

	@GetMapping("/combo/instituicoes")
	public List<ContasBancariaTO> getAllComboByInstituicaoLogada() {
		return getCmd.getAllComboByInstituicaoLogada();
	}
	
	
	@GetMapping("/instituicao/contascentrocustos")
	public List<ContasBancariaTO> findAllContasCentroCustos(@RequestParam(name = "dataReembolso", required = false) Long dataReembolso){
		return getCmd.findAllContasCentroCustos(dataReembolso);
	}
	
	@GetMapping("/{id}")
	public ContasBancariaTO getById(@PathVariable Long id) {
		return getCmd.getTOById(id);
	}

	@PostMapping
	public void cadastrar(@RequestBody ContasBancariaTO to) {
		cadastrarCmd.cadastrar(to);
	}

	@PutMapping
	public void alterar(@RequestBody ContasBancariaTO to) {
		alterarCmd.alterar(to);
	}

	@DeleteMapping("/{id}")
	public void excluir(@PathVariable Long id) {
		excluirCmd.excluir(id);
	}

}
