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

import br.com.crux.cmd.AlterarDepartamentoCmd;
import br.com.crux.cmd.CadastrarDepartamentoCmd;
import br.com.crux.cmd.ExcluirDepartamentoCmd;
import br.com.crux.cmd.GetDepartamentoCmd;
import br.com.crux.to.DepartamentoTO;

@RestController
@RequestMapping(value = "prestacoescontas")
public class PrestacoesContaService {

//	@Autowired private GetDepartamentoCmd getDepartamentoCmd;
//	@Autowired private ExcluirDepartamentoCmd excluirDepartamentoCmd;
//	@Autowired private AlterarDepartamentoCmd alterarDepartamentoCmd;
//	@Autowired private CadastrarDepartamentoCmd cadastrarDepartamentoCmd;

	@GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<DepartamentoTO> getAll() {
//		return getDepartamentoCmd.getAll();
		return null;
	}

	@GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public DepartamentoTO getById(@PathVariable(name = "id") Long idDepartamento) {
//		return getDepartamentoCmd.getTOById(idDepartamento);
		return null;
	}

	@PostMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void cadastrar(@RequestBody DepartamentoTO departamento) {
//		cadastrarDepartamentoCmd.cadastrar(departamento);
	}

	@PutMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void alterar(@RequestBody DepartamentoTO departamento) {
//		alterarDepartamentoCmd.alterar(departamento);
	}

	@DeleteMapping(path = "/{id}")
	public void excluir(@PathVariable(name = "id") Long idDepartamento) {
//		excluirDepartamentoCmd.excluir(idDepartamento);
	}

}
