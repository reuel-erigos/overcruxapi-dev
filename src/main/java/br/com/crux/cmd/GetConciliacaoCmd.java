package br.com.crux.cmd;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.ConciliacaoTOBuilder;
import br.com.crux.dao.ConciliacaoDao;
import br.com.crux.infra.util.Java8DateUtil;
import br.com.crux.rule.ValidarConciliacaoBancariaRule;
import br.com.crux.to.ConciliacaoTO;

@Component
public class GetConciliacaoCmd {

	@Autowired private ConciliacaoDao dao;
	@Autowired private ConciliacaoTOBuilder toBuilder;
	@Autowired private GetUnidadeLogadaCmd getUnidadeLogadaCmd;
	@Autowired private ValidarConciliacaoBancariaRule rule;

	public List<ConciliacaoTO> getAllFilter(Long idContaBancaria, Long dataInicio, Long dataFim) {
		rule.verificar(dataInicio, dataFim);
		
		LocalDate pDataInicio  = Java8DateUtil.getLocalDateTime(new Date(dataInicio)).toLocalDate();
		LocalDate pDataFim     = Java8DateUtil.getLocalDateTime(new Date(dataFim)).toLocalDate();
		
		Long idInstituicao = getUnidadeLogadaCmd.getUnidadeTO().getInstituicao().getId();
		//dao.gerar(idInstituicao, idContaBancaria, pDataInicio, pDataFim);
		
		return toBuilder.buildAll(dao.getAll(idInstituicao, idContaBancaria, pDataInicio, pDataFim));
	}



}
