package br.com.crux.cmd;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.ConciliacaoTOBuilder;
import br.com.crux.dao.GerarConciliacaoBancariaDao;
import br.com.crux.dao.GetConciliacaoBancariaDao;
import br.com.crux.dao.repository.ConciliacaoBancariaRepository;
import br.com.crux.entity.ConciliacaoBancaria;
import br.com.crux.infra.util.Java8DateUtil;
import br.com.crux.rule.ValidarConciliacaoBancariaRule;
import br.com.crux.to.ConciliacaoTO;

@Component
public class GetConciliacaoCmd {

	@Autowired private GerarConciliacaoBancariaDao gerarConciliacaoBancariaDao;
	@Autowired private GetConciliacaoBancariaDao getConciliacaoBancariaDao; 
	@Autowired private ConciliacaoTOBuilder toBuilder;
	@Autowired private GetUnidadeLogadaCmd getUnidadeLogadaCmd;
	@Autowired private ValidarConciliacaoBancariaRule rule;
	@Autowired private ConciliacaoBancariaRepository repository;

	public List<ConciliacaoTO> getAllFilter(Long idContaBancaria, Long dataInicio, Long dataFim) {
		rule.verificar(dataInicio, dataFim);
		
		LocalDate pDataInicio  = Java8DateUtil.getLocalDateTime(new Date(dataInicio)).toLocalDate();
		LocalDate pDataFim     = Java8DateUtil.getLocalDateTime(new Date(dataFim)).toLocalDate();
		
		Long idInstituicao = getUnidadeLogadaCmd.getUnidadeTO().getInstituicao().getId();
		return toBuilder.buildAll(getConciliacaoBancariaDao.getFilter(idInstituicao, idContaBancaria, pDataInicio, pDataFim));
	}

	public List<ConciliacaoTO> carregar(Long idContaBancaria, Long dataInicio, Long dataFim) {
		rule.verificar(dataInicio, dataFim);
		
		LocalDate pDataInicio  = Java8DateUtil.getLocalDateTime(new Date(dataInicio)).toLocalDate();
		LocalDate pDataFim     = Java8DateUtil.getLocalDateTime(new Date(dataFim)).toLocalDate();
		
		Long idInstituicao = getUnidadeLogadaCmd.getUnidadeTO().getInstituicao().getId();
		gerarConciliacaoBancariaDao.gerar(idInstituicao, idContaBancaria, pDataInicio, pDataFim);
		
		return toBuilder.buildAll(getConciliacaoBancariaDao.getFilter(idInstituicao, idContaBancaria, pDataInicio, pDataFim));
	}

	public List<ConciliacaoTO> getAllInconsistentes() {
		Optional<List<ConciliacaoBancaria>> inconsistentes = repository.findAllInconsistentes();
		if(inconsistentes.isPresent()) {
			return toBuilder.buildTOAll(inconsistentes.get());
		}
		return new ArrayList<>();
	}

}
