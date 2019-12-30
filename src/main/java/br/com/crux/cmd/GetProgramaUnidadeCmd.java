package br.com.crux.cmd;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.UnidadeTOBuilder;
import br.com.crux.dao.repository.UnidadeRepository;
import br.com.crux.entity.Unidade;
import br.com.crux.to.UnidadeTO;

@Component
public class GetProgramaUnidadeCmd {

	@Autowired
	UnidadeRepository unidadeRepository;
	@Autowired
	UnidadeTOBuilder unidadeTOBuilder;
	@Autowired
	SetUnidadeLogadaCmd setUnidadeLogadaCmd;

	public List<UnidadeTO> getUnidadesTOByIdPrograma(Long idPrograma) {
		List<Unidade> unidadesPorPrograma = unidadeRepository.getUnidadeByPrograma(idPrograma)
				.orElse(new ArrayList<Unidade>());

		List<UnidadeTO> listaUnidades = unidadeTOBuilder.buildAllTO(unidadesPorPrograma);

		setUnidadeLogadaCmd.set(listaUnidades);

		listaUnidades.sort(Comparator.comparing(UnidadeTO::getUnidadeLogada).reversed());

		return listaUnidades;

	}

}
