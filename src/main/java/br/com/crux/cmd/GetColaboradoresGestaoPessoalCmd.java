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

import br.com.crux.builder.ColaboradoresGestaoPessoalTOBuilder;
import br.com.crux.dao.ColaboradoresGestaoPessoalDao;
import br.com.crux.dao.dto.ColaboradoresGestaoPessoalDTO;
import br.com.crux.infra.util.Java8DateUtil;
import br.com.crux.to.ColaboradoresGestaoPessoalTO;

@Component
public class GetColaboradoresGestaoPessoalCmd {

	@Autowired private ColaboradoresGestaoPessoalDao       dao;
	@Autowired private ColaboradoresGestaoPessoalTOBuilder toBuilder;
	@Autowired private GetUnidadeLogadaCmd                 getUnidadeLogadaCmd;

	
	public List<ColaboradoresGestaoPessoalTO> getAllFilter(String cpf, Long idColaborador, Long idUnidade, Long idDepartamento, Long idCargo, Long idFuncao, Long dataInicio, Long dataFim) {
		Long idInstituicao = getUnidadeLogadaCmd.getUnidadeTO().getInstituicao().getId();

		Optional<List<ColaboradoresGestaoPessoalDTO>> entitys = Optional.empty();

		LocalDate pDataInicio = Objects.nonNull(dataInicio) ? Java8DateUtil.getLocalDateTime(new Date(dataInicio)).toLocalDate() : null;
		LocalDate pDataFim    = Objects.nonNull(dataFim) ? Java8DateUtil.getLocalDateTime(new Date(dataFim)).toLocalDate() : null;

		cpf             = StringUtils.isEmpty(cpf) ? null : cpf;
		idColaborador   = Objects.isNull(idColaborador) ? null : idColaborador;
		idUnidade       = Objects.isNull(idUnidade) ? null : idUnidade;
		idDepartamento  = Objects.isNull(idDepartamento) ? null : idDepartamento;		
		idCargo         = Objects.isNull(idCargo) ? null : idCargo;
		idFuncao        = Objects.isNull(idFuncao) ? null : idFuncao;

		entitys = dao.getAllFilter(cpf, idColaborador, idUnidade, idDepartamento, idCargo, idFuncao, pDataInicio, pDataFim, idInstituicao);

		if (entitys.isPresent()) {
			List<ColaboradoresGestaoPessoalTO> saldos = toBuilder.buildAllDTO(entitys.get());
			return saldos;
		}

		return new ArrayList<ColaboradoresGestaoPessoalTO>();
		
	}
	
	

	
}
