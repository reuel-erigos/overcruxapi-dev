package br.com.crux.cmd;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.dao.repository.GrupoModuloRepository;
import br.com.crux.dao.repository.InstituicaoRepository;
import br.com.crux.dao.repository.UsuarioSistemaRepository;
import br.com.crux.dao.repository.UsuariosGrupoRepository;
import br.com.crux.entity.GruposModulo;
import br.com.crux.entity.Instituicao;
import br.com.crux.entity.Modulo;
import br.com.crux.entity.UsuariosGrupo;
import br.com.crux.entity.UsuariosSistema;
import br.com.crux.exception.NotFoundException;
import br.com.crux.exception.PerfilAcessoException;
import br.com.crux.to.CadastroAcessoTO;

@Component
public class CadastrarAcessoUsuarioCmd {

	@Autowired private InstituicaoRepository instituicaoRepository;
	@Autowired private UsuariosGrupoRepository usuariosGrupoRepository;
	@Autowired private UsuarioSistemaRepository usuarioSistemaRepository;
	@Autowired private GetUsuarioLogadoCmd getUsuarioLogadoCmd;
	@Autowired private GrupoModuloRepository grupoModuloRepository;
	@Autowired private CadastrarGrupoModuloCmd cadastrarGrupoModuloCmd;
	
	
	public void cadastrarAll(List<CadastroAcessoTO> listaAcesso) {
		Optional.ofNullable(listaAcesso).ifPresent(lista -> {
			CadastroAcessoTO to = lista.get(0);
			
			Optional<Instituicao> instituicao = instituicaoRepository.findById(to.getIdInstituicao());
			if(!instituicao.isPresent()) {
				throw new NotFoundException("Instituicao informada não existe.");
			}
			
			Optional<UsuariosSistema> usuario = usuarioSistemaRepository.findById(to.getIdInstituicao());
			if(!usuario.isPresent()) {
				throw new NotFoundException("Usuario informado não existe.");
			}			
			
			lista.forEach(acessoTO -> {
				cadastrar(acessoTO, instituicao, usuario);
			});
		});
		
	}
	
	private void cadastrar(CadastroAcessoTO acessoTO, Optional<Instituicao> instituicao, Optional<UsuariosSistema> usuario) {

		Optional<GruposModulo> gruposModulo = grupoModuloRepository.findById(acessoTO.getIdGrupoModulo());
		if(!gruposModulo.isPresent()) {
			throw new NotFoundException("Não existe o tipo de perfil cadastrado para essa unidade.");
		}
		
		Optional<UsuariosGrupo> usuarioGrupo = usuariosGrupoRepository.findByGruposModuloAndUsuariosSistema(gruposModulo.get(), usuario.get());
		if(usuarioGrupo.isPresent()) {
			throw new PerfilAcessoException("Usuário já possui esse perfil cadastrado no módulo: " + gruposModulo.get().getModulo().getDescricao());
		}
		
		cadastrarAcessoModuloPai(instituicao, gruposModulo.get().getModulo(), usuario);
		
		UsuariosGrupo usuariosGrupo = new UsuariosGrupo();
		usuariosGrupo.setGruposModulo(gruposModulo.get());
		usuariosGrupo.setUsuariosSistema(usuario.get());
		usuariosGrupo.setIdUsuarioApl(getUsuarioLogadoCmd.getUsuarioLogado().getIdUsuario());
		usuariosGrupoRepository.save(usuariosGrupo);
		
	}

	
	private void cadastrarAcessoModuloPai(Optional<Instituicao> instituicao, Modulo modulo, Optional<UsuariosSistema> usuario) {
		if(Objects.isNull(modulo.getModuloPai())) return;
		
		Optional<List<UsuariosGrupo>> permissaoModuloPai = usuariosGrupoRepository.getPermissoes(usuario.get().getIdUsuario(), modulo.getModuloPai().getId(), instituicao.get().getId());
		if (!permissaoModuloPai.isPresent()) {
			
			//Valido se já existe permissão no módulo pai.
			GruposModulo gruposModuloPai = cadastrarGrupoModuloCmd.cadastrarGrupoModuloPai(instituicao.get().getId(), modulo.getModuloPai().getId());
						
			UsuariosGrupo usuariosGrupoPai = new UsuariosGrupo();
			usuariosGrupoPai.setGruposModulo(gruposModuloPai);
			usuariosGrupoPai.setUsuariosSistema(usuario.get());
			usuariosGrupoPai.setIdUsuarioApl(getUsuarioLogadoCmd.getUsuarioLogado().getIdUsuario());
			usuariosGrupoRepository.save(usuariosGrupoPai);
			
			
			cadastrarAcessoModuloPai(instituicao, modulo.getModuloPai() , usuario);
		}
	}
	
}
