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
			
			Optional<UsuariosSistema> usuario = usuarioSistemaRepository.findById(to.getIdUsuario());
			if(!usuario.isPresent()) {
				throw new NotFoundException("Usuario informado não existe.");
			}			
			
			lista.forEach(acessoTO -> {
				cadastrar(acessoTO, usuario);
			});
		});
		
	}
	
	private void cadastrar(CadastroAcessoTO acessoTO, Optional<UsuariosSistema> usuario) {

		//Optional<GruposModulo> gruposModulo = grupoModuloRepository.findById(acessoTO.getIdGrupoModulo());
		Optional<GruposModulo> gruposModulo = grupoModuloRepository.findByIdModuloAndIdPerfilAcessoAndIdInstituicao(acessoTO.getIdModulo(), 
				                                                                                                    acessoTO.getIdPerfilAcesso(), 
				                                                                                                    acessoTO.getIdInstituicao());
		if(!gruposModulo.isPresent()) {
			throw new NotFoundException("Não existe o tipo de perfil cadastrado para essa unidade.");
		}
		
		Optional<UsuariosGrupo> usuarioGrupo = usuariosGrupoRepository.findByGruposModuloAndUsuariosSistema(gruposModulo.get(), usuario.get());
		if(usuarioGrupo.isPresent()) {
			throw new PerfilAcessoException("Usuário já possui esse perfil cadastrado no módulo: " + gruposModulo.get().getModulo().getDescricao());
		}
		
		cadastrarAcessoModuloPai(acessoTO.getIdInstituicao(), gruposModulo.get().getModulo(), usuario);
		
		UsuariosGrupo usuariosGrupo = new UsuariosGrupo();
		usuariosGrupo.setGruposModulo(gruposModulo.get());
		usuariosGrupo.setUsuariosSistema(usuario.get());
		usuariosGrupo.setIdUsuarioApl(getUsuarioLogadoCmd.getUsuarioLogado().getIdUsuario());
		usuariosGrupoRepository.save(usuariosGrupo);
		
	}

	
	private void cadastrarAcessoModuloPai(Long idInstituicao, Modulo modulo, Optional<UsuariosSistema> usuario) {
		if(Objects.isNull(modulo.getModuloPai())) return;
		
		Optional<List<UsuariosGrupo>> permissaoModuloPai = usuariosGrupoRepository.getPermissoesByModuloPai(usuario.get().getIdUsuario(), modulo.getModuloPai().getId(), idInstituicao);
		if (!permissaoModuloPai.isPresent()) {
			
			//Valido se já existe permissão no módulo pai.
			GruposModulo gruposModuloPai = cadastrarGrupoModuloCmd.cadastrarGrupoModuloPai(idInstituicao, modulo.getModuloPai().getId());
						
			UsuariosGrupo usuariosGrupoPai = new UsuariosGrupo();
			usuariosGrupoPai.setGruposModulo(gruposModuloPai);
			usuariosGrupoPai.setUsuariosSistema(usuario.get());
			usuariosGrupoPai.setIdUsuarioApl(getUsuarioLogadoCmd.getUsuarioLogado().getIdUsuario());
			usuariosGrupoRepository.save(usuariosGrupoPai);
			
			
			cadastrarAcessoModuloPai(idInstituicao, modulo.getModuloPai() , usuario);
		}
	}
	
}
