package br.com.crux.cmd;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.ComposicaoRhProgramaTOBuilder;
import br.com.crux.dao.repository.ComposicaoRhProgramaRepository;
import br.com.crux.entity.ComposicaoRhPrograma;
import br.com.crux.entity.Programa;
import br.com.crux.to.ComposicaoRhProgramaTO;

@Component
public class GetComposicaoRhProgramaCmd {

	@Autowired private ComposicaoRhProgramaRepository repository;
	@Autowired private ComposicaoRhProgramaTOBuilder toBuilder;

	public List<ComposicaoRhProgramaTO> getComposicaoRhProgramaByPrograma(Programa param) {
		Optional<List<ComposicaoRhPrograma>> lista = repository.findByPrograma(param);

		if (lista.isPresent()) {
			return toBuilder.buildAll(lista.get());
		}

		return Collections.emptyList();

	}

}
