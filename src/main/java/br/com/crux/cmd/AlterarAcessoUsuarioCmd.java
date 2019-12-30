package br.com.crux.cmd;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.dao.repository.GrupoModuloRepository;
import br.com.crux.dao.repository.ModuloRepository;
import br.com.crux.dao.repository.UnidadeRepository;
import br.com.crux.dao.repository.UsuarioSistemaRepository;
import br.com.crux.dao.repository.UsuariosGrupoRepository;
import br.com.crux.entity.GruposModulo;
import br.com.crux.entity.UsuariosGrupo;
import br.com.crux.entity.UsuariosSistema;
import br.com.crux.exception.NotFoundException;
import br.com.crux.exception.PerfilAcessoException;
import br.com.crux.to.CadastroAcessoTO;

@Component
public class AlterarAcessoUsuarioCmd {

	@Autowired private UnidadeRepository unidadeRepository;
	@Autowired private ModuloRepository moduloRepository;
	@Autowired private UsuariosGrupoRepository usuariosGrupoRepository;
	@Autowired private UsuarioSistemaRepository usuarioSistemaRepository;
	@Autowired private GetUsuarioLogadoCmd getUsuarioLogadoCmd;
	@Autowired private GrupoModuloRepository grupoModuloRepository;

	public void alterar(CadastroAcessoTO acessoTO) {

		unidadeRepository.findById(acessoTO.getIdUnidade()).orElseThrow(() -> new NotFoundException("Unidade informada não existe."));

		moduloRepository.findById(acessoTO.getIdModulo()).orElseThrow(() -> new NotFoundException("Modulo informado não existe."));

		usuarioSistemaRepository.findById(acessoTO.getIdUsuario()).orElseThrow(() -> new NotFoundException("Usuario informado não existe."));

		GruposModulo gruposModulo = grupoModuloRepository.findById(acessoTO.getIdGrupoModulo())
				                                         .orElseThrow(() -> new NotFoundException("Não existe o tipo de perfil cadastrado para essa unidade."));

		UsuariosSistema usuarioLogado = usuarioSistemaRepository.findById(getUsuarioLogadoCmd.getUsuarioLogado().getIdUsuario())
				                                                .orElseThrow(() -> new NotFoundException("Usuario logado não existe."));

		UsuariosGrupo usuarioGrupo = usuariosGrupoRepository.getPorModuloAndGrupoModuloAndUsuario(gruposModulo.getModulo().getId(),gruposModulo.getId(),acessoTO.getIdUsuario())
				                                            .orElseThrow(() -> new PerfilAcessoException("Usuário grupo não encontrado."));
		

		usuarioGrupo.setGruposModulo(gruposModulo);
		usuarioGrupo.setIdUsuarioApl(usuarioLogado.getIdUsuario());
		usuariosGrupoRepository.save(usuarioGrupo);

	}
}
