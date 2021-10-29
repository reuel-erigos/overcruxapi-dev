package br.com.crux.cmd;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.MovimentacaoContabilTOBuilder;
import br.com.crux.dao.MovimentacaoContabilDao;
import br.com.crux.infra.util.Java8DateUtil;
import br.com.crux.to.relatorios.financeiro.MovimentacaoContabilDTO;
import br.com.crux.to.relatorios.financeiro.MovimentacaoContabilTO;

@Component
public class GetMovimentacaoContabilCmd {

	@Autowired private MovimentacaoContabilDao       dao;
	@Autowired private MovimentacaoContabilTOBuilder toBuilder;

	
	public List<MovimentacaoContabilTO> getAllFilter(Long idcategoria, Long idPrograma, Long idProjeto, Long dataInicio, Long dataFim) {
		Optional<List<MovimentacaoContabilDTO>> entitys = Optional.empty();

		LocalDate pDataInicio = Objects.nonNull(dataInicio) ? Java8DateUtil.getLocalDateTime(new Date(dataInicio)).toLocalDate() : null;
		LocalDate pDataFim    = Objects.nonNull(dataFim) ? Java8DateUtil.getLocalDateTime(new Date(dataFim)).toLocalDate() : null;

		idcategoria     = Objects.isNull(idcategoria) ? null : idcategoria;
		idPrograma      = Objects.isNull(idPrograma) ? null : idPrograma;
		idProjeto       = Objects.isNull(idProjeto) ? null : idProjeto;
		
		entitys = dao.getAllFilter(idcategoria, idPrograma, idProjeto, pDataInicio, pDataFim);

		if (entitys.isPresent()) {
			return toBuilder.buildAllDTO(entitys.get());
		}

		return new ArrayList<MovimentacaoContabilTO>();
		
	}
	
	

	
}
