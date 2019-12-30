package br.com.crux.service;

import java.util.List;

import javax.transaction.Transactional;

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

import br.com.crux.cmd.AlterarUsuariosSistemaCmd;
import br.com.crux.cmd.CadastrarUsuariosSistemaCmd;
import br.com.crux.cmd.ExcluirUsuarioSistemaCmd;
import br.com.crux.cmd.GetUsuarioSistemaCmd;
import br.com.crux.cmd.GetUsuariosPorUnidadeCmd;
import br.com.crux.to.UsuariosSistemaTO;

@RestController
@RequestMapping(value = "usuariosistema")
public class UsuarioSistemaService {

	@Autowired GetUsuariosPorUnidadeCmd getUsuariosPorUnidadeCmd;
	
	@Autowired private GetUsuarioSistemaCmd getCmd;
	@Autowired private ExcluirUsuarioSistemaCmd  excluirCmd;
	@Autowired private AlterarUsuariosSistemaCmd alterarCmd;
	@Autowired private CadastrarUsuariosSistemaCmd cadastrarCmd;	
	
	
	@GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<UsuariosSistemaTO> getAll() {
		return getCmd.getAll();
	}
	
	@GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public UsuariosSistemaTO getById(@PathVariable(name = "id") Long id) {
		return getCmd.getTOById(id);
	}
	
	@PostMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE)
	public UsuariosSistemaTO cadastrar(@RequestBody UsuariosSistemaTO param) {
		return cadastrarCmd.cadastrar(param);
	}
	
	@PutMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE)
	public UsuariosSistemaTO alterar(@RequestBody UsuariosSistemaTO param) {
		return alterarCmd.alterar(param);
	}
	
	@DeleteMapping(path = "/{id}")
	@Transactional
	public void excluir(@PathVariable(name = "id") Long id) {
		excluirCmd.excluir(id);
	}	
}
