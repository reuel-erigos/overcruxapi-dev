package br.com.crux.cmd;

import java.time.LocalDate;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.dao.GerarProvisionamentoDao;
import br.com.crux.exception.ProvisionamentoNaoGeradoException;
import br.com.crux.infra.util.Java8DateUtil;
import br.com.crux.rule.ValidarProvisionamentoRule;

@Component
public class GerarProvisionamentoCmd {

	@Autowired private GerarProvisionamentoDao dao;
	@Autowired private GetUnidadeLogadaCmd getUnidadeLogadaCmd;
	@Autowired private ValidarProvisionamentoRule rule;

	public void gerar(Long dataInicio, Long dataFim) {
		rule.verificar(dataInicio, dataFim);
		
		try {
			LocalDate pDataInicio  = Java8DateUtil.getLocalDateTime(new Date(dataInicio)).toLocalDate();
			LocalDate pDataFim     = Java8DateUtil.getLocalDateTime(new Date(dataFim)).toLocalDate();
			
			Long idInstituicao = getUnidadeLogadaCmd.getUnidadeTO().getInstituicao().getId();
			dao.gerar(idInstituicao, pDataInicio, pDataFim);
			
		} catch (Exception e) {
			throw new ProvisionamentoNaoGeradoException(e.getMessage());
		}
	}


}
