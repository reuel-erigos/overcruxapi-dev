package br.com.crux.cmd;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.ProvisionamentoTOBuilder;
import br.com.crux.dao.GerarProvisionamentoDao;
import br.com.crux.dao.GetProvisionamentoDao;
import br.com.crux.infra.util.Java8DateUtil;
import br.com.crux.rule.ValidarProvisionamentoRule;
import br.com.crux.to.ProvisionamentoTO;

@Component
public class GetProvisionamentoCmd {

	@Autowired private GerarProvisionamentoDao gerarDao;
	@Autowired private GetProvisionamentoDao getDao; 
	@Autowired private ProvisionamentoTOBuilder toBuilder;
	@Autowired private GetUnidadeLogadaCmd getUnidadeLogadaCmd;
	@Autowired private ValidarProvisionamentoRule rule;

	public List<ProvisionamentoTO> getAllFilter(Long dataInicio, Long dataFim) {
		rule.verificar(dataInicio, dataFim);
		
		LocalDate pDataInicio  = Java8DateUtil.getLocalDateTime(new Date(dataInicio)).toLocalDate();
		LocalDate pDataFim     = Java8DateUtil.getLocalDateTime(new Date(dataFim)).toLocalDate();
		
		Long idInstituicao = getUnidadeLogadaCmd.getUnidadeTO().getInstituicao().getId();
		return toBuilder.buildAll(getDao.getFilter(idInstituicao, pDataInicio, pDataFim));
	}

	public List<ProvisionamentoTO> carregar(Long dataInicio, Long dataFim) {
		rule.verificar(dataInicio, dataFim);
		
		LocalDate pDataInicio  = Java8DateUtil.getLocalDateTime(new Date(dataInicio)).toLocalDate();
		LocalDate pDataFim     = Java8DateUtil.getLocalDateTime(new Date(dataFim)).toLocalDate();
		
		Long idInstituicao = getUnidadeLogadaCmd.getUnidadeTO().getInstituicao().getId();
		gerarDao.gerar(idInstituicao, pDataInicio, pDataFim);
		
		return toBuilder.buildAll(getDao.getFilter(idInstituicao, pDataInicio, pDataFim));
	}


}
