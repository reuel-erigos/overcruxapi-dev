package br.com.crux.cmd;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.dao.repository.UsuariosGrupoRepository;
import br.com.crux.entity.Modulo;
import br.com.crux.entity.UsuariosGrupo;
import br.com.crux.entity.UsuariosSistema;
import br.com.crux.exception.ParametroNaoInformadoException;

@Component
public class ExcluirAcessoUsuarioCmd {

	@Autowired private UsuariosGrupoRepository usuariosGrupoRepository;

	public void excluir(Long idUsuarioGrupo) {
		if(Objects.isNull(idUsuarioGrupo)) {
			throw new ParametroNaoInformadoException("Erro ao excluir o acesso do usuário. Parâmetro 'usuarioGrupo' ausente.");
		}
		
		Optional<UsuariosGrupo> usuarioGrupo = usuariosGrupoRepository.getById(idUsuarioGrupo);
		usuariosGrupoRepository.deleteById(idUsuarioGrupo);

		UsuariosSistema usuario = usuarioGrupo.get().getUsuariosSistema();
		Modulo modulo = usuarioGrupo.get().getGruposModulo().getModulo();
		
		apagarPermissaoModuloPai(usuario, modulo);
	}

	
	private void apagarPermissaoModuloPai(UsuariosSistema usuario, Modulo modulo) {
		if(Objects.isNull(modulo.getModuloPai())) return;
		
		// Busco outras permissões de módulos filhos com o mesmo módulo pai
		Optional<List<UsuariosGrupo>> usuariosGrupos = usuariosGrupoRepository.getModulosFilhosComMesmoModuloPai(usuario.getIdUsuario(), modulo.getModuloPai().getId());

		if(!usuariosGrupos.isPresent()) {
			// Significa que existe permissão com modulo pai sem modulo filho.
			Optional<List<UsuariosGrupo>> modulosPai = usuariosGrupoRepository.getModulosPai(usuario.getIdUsuario(), modulo.getModuloPai());
			
			//Apaga a permissão do módulo pai.
			if(modulosPai.isPresent()) {
				modulosPai.get().stream().forEach( mp -> usuariosGrupoRepository.deleteById(mp.getIdUsuarioGrupo()));
				
				apagarPermissaoModuloPai(usuario, modulo.getModuloPai());
			}
		}
	}
}
