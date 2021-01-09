package br.com.crux.cmd;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.NormativaPagamentosTOBuilder;
import br.com.crux.dao.NormativaPagamentosDao;
import br.com.crux.infra.util.Java8DateUtil;
import br.com.crux.to.relatorios.financeiro.NormativaPagamentosDTO;
import br.com.crux.to.relatorios.financeiro.NormativaPagamentosTO;

@Component
public class GetNormativaPagamentosCmd {

	@Autowired private NormativaPagamentosDao       dao;
	@Autowired private NormativaPagamentosTOBuilder toBuilder;

	
	public List<NormativaPagamentosTO> getAllFilter(String categoria, String cnpjCpf, String programaProjeto, Long dataInicio, Long dataFim) {
		Optional<List<NormativaPagamentosDTO>> entitys = Optional.empty();

		LocalDate pDataInicio = Objects.nonNull(dataInicio) ? Java8DateUtil.getLocalDateTime(new Date(dataInicio)).toLocalDate() : null;
		LocalDate pDataFim    = Objects.nonNull(dataFim) ? Java8DateUtil.getLocalDateTime(new Date(dataFim)).toLocalDate() : null;

		categoria       = StringUtils.isEmpty(categoria) ? null : categoria;
		cnpjCpf         = StringUtils.isEmpty(cnpjCpf) ? null : cnpjCpf;
		programaProjeto = StringUtils.isEmpty(programaProjeto) ? null : programaProjeto;

		entitys = dao.getAllFilter(categoria, cnpjCpf, programaProjeto, pDataInicio, pDataFim);

		if (entitys.isPresent()) {
			return toBuilder.buildAllDTO(entitys.get());
		}

		return new ArrayList<NormativaPagamentosTO>();
		
	}
	
	

	
}
