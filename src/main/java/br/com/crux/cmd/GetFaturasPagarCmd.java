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

import br.com.crux.builder.FaturasPagarTOBuilder;
import br.com.crux.dao.FaturasPagarDao;
import br.com.crux.infra.util.Java8DateUtil;
import br.com.crux.to.relatorios.financeiro.FaturasPagarDTO;
import br.com.crux.to.relatorios.financeiro.FaturasPagarTO;

@Component
public class GetFaturasPagarCmd {

	@Autowired private FaturasPagarDao       dao;
	@Autowired private FaturasPagarTOBuilder toBuilder;

	
	public List<FaturasPagarTO> getAllFilter(String categoria, String cnpjCpf, String programaProjeto, Long dataInicio, Long dataFim, Long dataInicioVenc, Long dataFimVenc) {
		Optional<List<FaturasPagarDTO>> entitys = Optional.empty();

		LocalDate pDataInicio     = Objects.nonNull(dataInicio) ? Java8DateUtil.getLocalDateTime(new Date(dataInicio)).toLocalDate() : null;
		LocalDate pDataFim        = Objects.nonNull(dataFim) ? Java8DateUtil.getLocalDateTime(new Date(dataFim)).toLocalDate() : null;
		LocalDate pDataInicioVenc = Objects.nonNull(dataInicioVenc) ? Java8DateUtil.getLocalDateTime(new Date(dataInicioVenc)).toLocalDate() : null;
		LocalDate pDataFimVenc    = Objects.nonNull(dataFimVenc) ? Java8DateUtil.getLocalDateTime(new Date(dataFimVenc)).toLocalDate() : null;

		categoria       = StringUtils.isEmpty(categoria) ? null : categoria;
		cnpjCpf         = StringUtils.isEmpty(cnpjCpf) ? null : cnpjCpf;
		programaProjeto = StringUtils.isEmpty(programaProjeto) ? null : programaProjeto;

		entitys = dao.getAllFilter(categoria, cnpjCpf, programaProjeto, pDataInicio, pDataFim, pDataInicioVenc, pDataFimVenc);

		if (entitys.isPresent()) {
			return toBuilder.buildAllDTO(entitys.get());
		}

		return new ArrayList<FaturasPagarTO>();
		
	}
	
	

	
}
