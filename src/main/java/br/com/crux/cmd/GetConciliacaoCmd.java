package br.com.crux.cmd;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.ConciliacaoTOBuilder;
import br.com.crux.dao.ConciliacaoDao;
import br.com.crux.infra.util.Java8DateUtil;
import br.com.crux.to.ConciliacaoTO;

@Component
public class GetConciliacaoCmd {

	@Autowired private ConciliacaoDao dao;
	@Autowired private ConciliacaoTOBuilder toBuilder;
	@Autowired private GetUnidadeLogadaCmd getUnidadeLogadaCmd;


	public List<ConciliacaoTO> getAllFilter(Long idContaBancaria, Long dataInicio, Long dataFim) {
		LocalDate pDataInicio  = Objects.nonNull(dataInicio) ? Java8DateUtil.getLocalDateTime(new Date(dataInicio)).toLocalDate() : null;
		LocalDate pDataFim     = Objects.nonNull(dataFim) ? Java8DateUtil.getLocalDateTime(new Date(dataFim)).toLocalDate() : null;
		
		Long idInstituicao = getUnidadeLogadaCmd.getUnidadeTO().getInstituicao().getId();
		return toBuilder.buildAll(dao.getAll(idInstituicao, idContaBancaria, pDataInicio, pDataFim));
	}



}
