package br.com.crux.cmd;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.DadosUsuarioUnidadeTOBuilder;
import br.com.crux.dao.GetUsuarioSistemaDao;
import br.com.crux.dao.dto.UsuarioUnidadeDTO;
import br.com.crux.exception.ParametroNaoInformadoException;
import br.com.crux.to.DadosUsuarioUnidadeTO;

@Component
public class GetUsuariosPorUnidadeCmd {

	@Autowired private GetUsuarioSistemaDao getUsuarioSistemaDao;
	@Autowired private DadosUsuarioUnidadeTOBuilder usuarioUnidadeTOBuilder;
	@Autowired private GetUnidadeLogadaCmd getUnidadeLogadaCmd;

	public List<DadosUsuarioUnidadeTO> getUsuariosPorUnidade() {
		Long idUnidade = getUnidadeLogadaCmd.get().getId();
		List<UsuarioUnidadeDTO> entitys = getUsuarioSistemaDao.getUsuariosPorUnidade(idUnidade);
		if(entitys == null || entitys.isEmpty()) {
			return new ArrayList<DadosUsuarioUnidadeTO>();
		}
		return usuarioUnidadeTOBuilder.buildAll(entitys);
	}

	
	public List<DadosUsuarioUnidadeTO> getUsuariosPorUnidade(Long idUnidade) {
		if (Objects.isNull(idUnidade)) {
			throw new ParametroNaoInformadoException("A unidade não foi informada para buscar os usuários.");
		}
		return usuarioUnidadeTOBuilder.buildAll(getUsuarioSistemaDao.getUsuariosPorUnidade(idUnidade));
	}
}
