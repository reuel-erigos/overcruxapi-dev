package br.com.crux.cmd;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.ModuloTOBuilder;
import br.com.crux.dao.ModuloDao;
import br.com.crux.dao.repository.ModuloRepository;
import br.com.crux.entity.Modulo;
import br.com.crux.to.ModuloTO;

@Component
public class GetModulosCmd {

	@Autowired private ModuloDao moduloDao;
	@Autowired private ModuloTOBuilder moduloTOBuilder;
	@Autowired private GetUnidadeLogadaCmd getUnidadeLogadaCmd;
	@Autowired private ModuloRepository moduloRepository;

	public List<ModuloTO> getAll() {
		List<Modulo> modulos = moduloRepository.findAllByOrderByDescricaoAsc().stream().filter(m -> Objects.nonNull(m.getModuloPai()))
				.collect(Collectors.toList());
		return moduloTOBuilder.buildAll(modulos);
	}

	public List<ModuloTO> getModulosPorUnidadeLogada() {
		return getModulosPorUnidade(getUnidadeLogadaCmd.get().getId());
	}

	public List<ModuloTO> getModulosPorUnidade(Long idUnidade) {
		return moduloTOBuilder.buildAllDTO(moduloDao.getModuloPorUnidade(idUnidade));
	}
}
