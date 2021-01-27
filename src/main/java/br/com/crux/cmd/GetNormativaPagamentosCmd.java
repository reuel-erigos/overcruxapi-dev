package br.com.crux.cmd;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

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

	
	public List<NormativaPagamentosTO> getAllFilter(Long idcategoria, Long idEmpresa, Long idPessoaFisica, Long idPrograma, Long idProjeto, Long dataInicio, Long dataFim) {
		Optional<List<NormativaPagamentosDTO>> entitys = Optional.empty();

		LocalDate pDataInicio = Objects.nonNull(dataInicio) ? Java8DateUtil.getLocalDateTime(new Date(dataInicio)).toLocalDate() : null;
		LocalDate pDataFim    = Objects.nonNull(dataFim) ? Java8DateUtil.getLocalDateTime(new Date(dataFim)).toLocalDate() : null;

		idcategoria     = Objects.isNull(idcategoria) ? null : idcategoria;
		idEmpresa       = Objects.isNull(idEmpresa) ? null : idEmpresa;
		idPessoaFisica  = Objects.isNull(idPessoaFisica) ? null : idPessoaFisica;
		idPrograma      = Objects.isNull(idPrograma) ? null : idPrograma;
		idProjeto       = Objects.isNull(idProjeto) ? null : idProjeto;
		
		entitys = dao.getAllFilter(idcategoria, idEmpresa, idPessoaFisica, idPrograma, idProjeto, pDataInicio, pDataFim);

		if (entitys.isPresent()) {
			return toBuilder.buildAllDTO(entitys.get());
		}

		return new ArrayList<NormativaPagamentosTO>();
		
	}
	
	

	
}
