package br.com.crux.cmd;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Component;

import br.com.crux.builder.SerieEscolarTOBuilder;
import br.com.crux.dao.repository.SerieEscolarRepository;
import br.com.crux.entity.SerieEscolar;
import br.com.crux.to.SerieEscolarTO;

@Component
public class GetSerieEscolarCmd {

	@Autowired private SerieEscolarRepository repository;
	@Autowired private SerieEscolarTOBuilder toBuilder;
	
	public List<SerieEscolarTO> getAll() {
		Sort sort = Sort.by(Direction.ASC, "descricao");
		List<SerieEscolar> escolas = repository.findAll(sort);
		return toBuilder.buildAll(escolas);
	}


}
