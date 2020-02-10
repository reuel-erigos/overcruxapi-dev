package br.com.crux.cmd;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.ModuloTOBuilder;
import br.com.crux.dao.ModuloDao;
import br.com.crux.to.ModuloTO;

@Component
public class GetModulosCmd {

	@Autowired private ModuloDao moduloDao;
	@Autowired private ModuloTOBuilder moduloTOBuilder;
	@Autowired private GetUnidadeLogadaCmd getUnidadeLogadaCmd;

	public List<ModuloTO> getAllModulo() {
		return moduloTOBuilder.buildAllDTO(moduloDao.getAllModulo());
	}

	public List<ModuloTO> getModulosPorInstituicaoLogada() {
		return getModulosPorInstituicao(getUnidadeLogadaCmd.getUnidadeTO().getInstituicao().getId());
	}

	public List<ModuloTO> getModulosPorInstituicao(Long idInstituicao) {
		return moduloTOBuilder.buildAllDTO(moduloDao.getModuloPorInstituicao(idInstituicao));
	}
}
