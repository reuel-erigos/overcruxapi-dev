package br.com.crux.cmd;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.SaldoProjetoTOBuilder;
import br.com.crux.dao.SaldoProjetoDao;
import br.com.crux.infra.util.Java8DateUtil;
import br.com.crux.to.relatorios.financeiro.SaldoProjetoDTO;
import br.com.crux.to.relatorios.financeiro.SaldoProjetoTO;

@Component
public class GetSaldoProjetoCmd {

	@Autowired private SaldoProjetoDao       dao;
	@Autowired private SaldoProjetoTOBuilder toBuilder;
	@Autowired private GetUnidadeLogadaCmd getUnidadeLogadaCmd;

	
	public List<SaldoProjetoTO> getAllFilter(Long idPrograma, Long idProjeto, Long dataInicio, Long dataFim) {
		Optional<List<SaldoProjetoDTO>> entitys = Optional.empty();

		LocalDate pDataInicio     = Objects.nonNull(dataInicio) ? Java8DateUtil.getLocalDateTime(new Date(dataInicio)).toLocalDate() : null;
		LocalDate pDataFim        = Objects.nonNull(dataFim) ? Java8DateUtil.getLocalDateTime(new Date(dataFim)).toLocalDate() : null;

		Long idInstituicao = getUnidadeLogadaCmd.getUnidadeTO().getInstituicao().getId();
		idPrograma         = Objects.isNull(idPrograma) ? null : idPrograma;
		idProjeto          = Objects.isNull(idProjeto) ? null : idProjeto;
		
		entitys = Optional.ofNullable(dao.getAllFilter(idInstituicao, idPrograma, idProjeto, pDataInicio, pDataFim));

		if (entitys.isPresent()) {
			return toBuilder.buildAllDTO(entitys.get());
		}

		return new ArrayList<SaldoProjetoTO>();
		
	}
	
	

	
}
