package br.com.crux.service;

import java.util.List;

import br.com.crux.builder.ExcluirBeneficilSocialCmd;
import br.com.crux.cmd.CadastrarBeneficioSocialCmd;
import br.com.crux.cmd.ExcluirContasBancariaCmd;
import br.com.crux.to.AlunoTO;
import br.com.crux.to.AvaliacoesTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import br.com.crux.cmd.GetBeneficioSocialCmd;
import br.com.crux.to.BeneficioSocialTO;

@RestController
@RequestMapping(value = "beneficiossociais")
public class BeneficioSocialService {

	@Autowired private GetBeneficioSocialCmd getCmd;
	@Autowired private CadastrarBeneficioSocialCmd cadastrarBeneficioSocialCmd;
	@Autowired private ExcluirBeneficilSocialCmd excluirBeneficilSocialCmd;
	
	@GetMapping
	public List<BeneficioSocialTO> getAll() {
		return getCmd.getAll();
	}

	@GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public BeneficioSocialTO getById(@PathVariable(name = "id") Long id) {
		return getCmd.getTOById(id);
	}

	@DeleteMapping(path = "/{id}")
	public void excluir(@PathVariable(name = "id") Long id) {
		excluirBeneficilSocialCmd.excluir(id);
	}


	@PostMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE)
	@Transactional
	public BeneficioSocialTO cadastrar(@RequestBody BeneficioSocialTO param) {
		return cadastrarBeneficioSocialCmd.cadastrar(param);
	}

}
