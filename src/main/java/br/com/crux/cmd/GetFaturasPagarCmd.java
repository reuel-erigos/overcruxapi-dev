package br.com.crux.cmd;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.FaturasPagarTOBuilder;
import br.com.crux.dao.FaturasPagarDao;
import br.com.crux.infra.util.Java8DateUtil;
import br.com.crux.to.relatorios.financeiro.FaturasPagarDTO;
import br.com.crux.to.relatorios.financeiro.FaturasPagarTO;
import br.com.crux.to.relatorios.financeiro.FaturasPagarViewDTO;

@Component
public class GetFaturasPagarCmd {

	@Autowired private FaturasPagarDao       dao;
	@Autowired private FaturasPagarTOBuilder toBuilder;
	
	public List<FaturasPagarTO> getAllFilter(Long idcategoria, Long idEmpresa, Long idPessoaFisica, Long idPrograma, Long idProjeto, Long dataInicio, Long dataFim, Long dataInicioVenc, Long dataFimVenc) {
		Optional<List<FaturasPagarDTO>> entitys = Optional.empty();

		LocalDate pDataInicio     = Objects.nonNull(dataInicio) ? Java8DateUtil.getLocalDateTime(new Date(dataInicio)).toLocalDate() : null;
		LocalDate pDataFim        = Objects.nonNull(dataFim) ? Java8DateUtil.getLocalDateTime(new Date(dataFim)).toLocalDate() : null;
		LocalDate pDataInicioVenc = Objects.nonNull(dataInicioVenc) ? Java8DateUtil.getLocalDateTime(new Date(dataInicioVenc)).toLocalDate() : null;
		LocalDate pDataFimVenc    = Objects.nonNull(dataFimVenc) ? Java8DateUtil.getLocalDateTime(new Date(dataFimVenc)).toLocalDate() : null;

		idcategoria     = Objects.isNull(idcategoria) ? null : idcategoria;
		idEmpresa       = Objects.isNull(idEmpresa) ? null : idEmpresa;
		idPessoaFisica  = Objects.isNull(idPessoaFisica) ? null : idPessoaFisica;
		idPrograma      = Objects.isNull(idPrograma) ? null : idPrograma;
		idProjeto       = Objects.isNull(idProjeto) ? null : idProjeto;

		entitys = dao.getAllFilter(idcategoria, idEmpresa, idPessoaFisica, idPrograma, idProjeto, pDataInicio, pDataFim, pDataInicioVenc, pDataFimVenc);

		if (entitys.isPresent()) {
			return toBuilder.buildAllDTO(entitys.get());
		}

		return new ArrayList<FaturasPagarTO>();
		
	}
	
	public List<FaturasPagarViewDTO> getAllFilterView(Long idcategoria, Long idEmpresa, Long idPessoaFisica, Long idPrograma, Long idProjeto, Long dataInicio, Long dataFim, Long dataInicioVenc, Long dataFimVenc) {
		Optional<List<FaturasPagarViewDTO>> entitys = Optional.empty();

		LocalDate pDataInicio     = Objects.nonNull(dataInicio) ? Java8DateUtil.getLocalDateTime(new Date(dataInicio)).toLocalDate() : null;
		LocalDate pDataFim        = Objects.nonNull(dataFim) ? Java8DateUtil.getLocalDateTime(new Date(dataFim)).toLocalDate() : null;
		LocalDate pDataInicioVenc = Objects.nonNull(dataInicioVenc) ? Java8DateUtil.getLocalDateTime(new Date(dataInicioVenc)).toLocalDate() : null;
		LocalDate pDataFimVenc    = Objects.nonNull(dataFimVenc) ? Java8DateUtil.getLocalDateTime(new Date(dataFimVenc)).toLocalDate() : null;

		idcategoria     = Objects.isNull(idcategoria) ? null : idcategoria;
		idEmpresa       = Objects.isNull(idEmpresa) ? null : idEmpresa;
		idPessoaFisica  = Objects.isNull(idPessoaFisica) ? null : idPessoaFisica;
		idPrograma      = Objects.isNull(idPrograma) ? null : idPrograma;
		idProjeto       = Objects.isNull(idProjeto) ? null : idProjeto;

		entitys = dao.getAllFilterView(idcategoria, idEmpresa, idPessoaFisica, idPrograma, idProjeto, pDataInicio, pDataFim, pDataInicioVenc, pDataFimVenc);

		if (entitys.isPresent()) {
			return entitys.get();
		}

		return new ArrayList<FaturasPagarViewDTO>();
		
	}

	

	
}
