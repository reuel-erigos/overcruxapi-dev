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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.crux.cmd.AlterarAcessoUsuarioCmd;
import br.com.crux.cmd.CadastrarAcessoUsuarioCmd;
import br.com.crux.cmd.ExcluirAcessoUsuarioCmd;
import br.com.crux.cmd.GetAcessoCmd;
import br.com.crux.to.CadastroAcessoTO;
import br.com.crux.to.AcessoTO;
import br.com.crux.to.PerfilAcessoUsuarioTO;

@RestController
@RequestMapping(value = "acesso")
public class AcessoService {
	
	@Autowired
	private ExcluirAcessoUsuarioCmd excluirAcessoUsuarioCmd;
	@Autowired
	private CadastrarAcessoUsuarioCmd cadastrarAcessoUsuarioCmd;
	@Autowired
	private AlterarAcessoUsuarioCmd alterarAcessoUsuarioCmd;
	@Autowired
	private GetAcessoCmd getPerfilAcessoCmd;

	/*
	@PostMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void cadastrar(@RequestBody CadastroAcessoTO acesso) {
		cadastrarAcessoUsuarioCmd.cadastrar(acesso);
	}
	*/

	@PostMapping(path = "/all", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void cadastrarAll(@RequestBody List<CadastroAcessoTO> listaAcesso) {
		cadastrarAcessoUsuarioCmd.cadastrarAll(listaAcesso);
	}

	
	@PutMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void alterar(@RequestBody CadastroAcessoTO acesso) {
		alterarAcessoUsuarioCmd.alterar(acesso);
	}
	
	@DeleteMapping(path = "/{id}")
	public void excluir(@PathVariable(name = "id") Long idUsuarioGrupo) {
		excluirAcessoUsuarioCmd.excluir(idUsuarioGrupo);
	}
	
	//Usado na tela de CADASTRO de acesso para um usuário específico
	@GetMapping(path = "/perfil/unidade/{idUnidade}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<PerfilAcessoUsuarioTO> getPerfilAcessoDoUsuario(@PathVariable(name = "idUnidade") Long idUnidade, 
			                                                    @RequestParam(name = "usuario", required = false) Long idUsuario, 
			                                                    @RequestParam(name = "modulo", required = false) Long idModulo) {
		return getPerfilAcessoCmd.getPerfilAcessoDoUsuario(idUnidade, idUsuario, idModulo);
	}
	

	//Usado na tela de CONSULTA de acesso para um usuário específico
	@GetMapping(path = "/perfil/unidadelogada", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<PerfilAcessoUsuarioTO> getPerfilAcessoDoUsuarioNaUnidadeLogada( @RequestParam(name = "usuario", required = false) Long idUsuario, 
			                                                                    @RequestParam(name = "modulo", required = false) Long idModulo) {
		return getPerfilAcessoCmd.getPerfilAcessoDoUsuarioNaUnidadeLogada(idUsuario, idModulo);
	}
	
	
	
	//Retorna os acesso do módulo acessado no menu
	@GetMapping(path = "/modulo/{nome}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<AcessoTO> getPerfilAcesso(@PathVariable(name = "nome") String nomeModulo) {
		return getPerfilAcessoCmd.getPerfilAcesso(nomeModulo);
	}

}
