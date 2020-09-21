package br.com.crux.cmd;

import java.time.LocalDate;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.dao.ConciliacaoDao;
import br.com.crux.exception.ConciliacaoNaoGeradoException;
import br.com.crux.infra.util.Java8DateUtil;
import br.com.crux.rule.ValidarConciliacaoBancariaRule;

@Component
public class GerarConciliacaoCmd {

	@Autowired private ConciliacaoDao dao;
	@Autowired private GetUnidadeLogadaCmd getUnidadeLogadaCmd;
	@Autowired private ValidarConciliacaoBancariaRule rule;

	public void gerar(Long idContaBancaria, Long dataInicio, Long dataFim) {
		rule.verificar(dataInicio, dataFim);
		
		try {
			LocalDate pDataInicio  = Java8DateUtil.getLocalDateTime(new Date(dataInicio)).toLocalDate();
			LocalDate pDataFim     = Java8DateUtil.getLocalDateTime(new Date(dataFim)).toLocalDate();
			
			Long idInstituicao = getUnidadeLogadaCmd.getUnidadeTO().getInstituicao().getId();
			dao.gerar(idInstituicao, idContaBancaria, pDataInicio, pDataFim);
			
		} catch (Exception e) {
			throw new ConciliacaoNaoGeradoException(e.getMessage());
		}
	}


}
