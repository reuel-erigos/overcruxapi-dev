package br.com.crux.cmd;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.UsuariosUnidadeTOBuilder;
import br.com.crux.dao.repository.UsuariosUnidadeRepository;
import br.com.crux.entity.UsuariosUnidade;
import br.com.crux.to.UsuariosUnidadesTO;

@Component
public class GetUsuarioUnidadeCmd {

	@Autowired private UsuariosUnidadeRepository usuariosUnidadeRepository;
	@Autowired private UsuariosUnidadeTOBuilder usuariosUnidadeTOBuilder;
	@Autowired private GetUsuarioLogadoCmd getUsuarioLogadoCmd;
	@Autowired private GetUnidadeLogadaCmd getUnidadeLogadaCmd;
	
	
	public List<UsuariosUnidadesTO> getUnidadesUsuarioLogadoComAcesso() {
		return getUnidadesComAcesso(getUsuarioLogadoCmd.getUsuarioLogado().getIdUsuario());
	}
	
	public List<UsuariosUnidadesTO> getUnidadesComAcesso(Long idUsuario) {
		Long idInstituicao = getUnidadeLogadaCmd.getUnidadeTO().getInstituicao().getId();
		
		List<UsuariosUnidade> unidades = usuariosUnidadeRepository.findAllByIdUsuarioAndIdInstituicao(idUsuario, idInstituicao)
				                                                 .orElse(Collections.emptyList());
		
		return usuariosUnidadeTOBuilder.buildAll(unidades);
	}

}
