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

import br.com.crux.builder.ExportacaoDadosAlunoTOBuilder;
import br.com.crux.dao.ExportacaoDadosAlunoDao;
import br.com.crux.dao.dto.ExportacaoDadosAlunoDTO;
import br.com.crux.infra.util.Java8DateUtil;
import br.com.crux.to.ExportacaoDadosAlunoTO;

@Component
public class GetExportacaoDadosAlunoCmd {

	@Autowired private ExportacaoDadosAlunoDao       dao;
	@Autowired private ExportacaoDadosAlunoTOBuilder toBuilder;
	@Autowired private GetUnidadeLogadaCmd           getUnidadeLogadaCmd;

	
	public List<ExportacaoDadosAlunoTO> getAllFilter(String cpf, Long idBeneficiario, Long idMae, Long idPai, 
			                                         Long idPrograma, Long idProjeto, Long idUnidade, Long idResponsavel, 
                                                     Long dataInicioInstituicao, Long dataFimInstituicao
            ) {
		Long idInstituicao = getUnidadeLogadaCmd.getUnidadeTO().getInstituicao().getId();

		Optional<List<ExportacaoDadosAlunoDTO>> entitys = Optional.empty();

		LocalDate pDataInicioInstituicao  = Objects.nonNull(dataInicioInstituicao) ? Java8DateUtil.getLocalDateTime(new Date(dataInicioInstituicao)).toLocalDate() : null;
		LocalDate pDataFimInstituicao     = Objects.nonNull(dataFimInstituicao) ? Java8DateUtil.getLocalDateTime(new Date(dataFimInstituicao)).toLocalDate() : null;

		cpf             = StringUtils.isEmpty(cpf) ? null : cpf;
		idBeneficiario  = Objects.isNull(idBeneficiario) ? null : idBeneficiario;
		idMae           = Objects.isNull(idMae) ? null : idMae;
		idPai           = Objects.isNull(idPai) ? null : idPai;
		idPrograma      = Objects.isNull(idPrograma) ? null : idPrograma;
		idProjeto       = Objects.isNull(idProjeto) ? null : idProjeto;
		idUnidade       = Objects.isNull(idUnidade) ? null : idUnidade;
		idResponsavel   = Objects.isNull(idResponsavel) ? null : idResponsavel;		

		entitys = dao.getAllFilter(cpf, idBeneficiario, idMae, idPai, 
				                   idPrograma, idProjeto, idUnidade, 
				                   idResponsavel, pDataInicioInstituicao, pDataFimInstituicao,
				                   idInstituicao);

		if (entitys.isPresent()) {
			List<ExportacaoDadosAlunoTO> saldos = toBuilder.buildAllDTO(entitys.get());
			return saldos;
		}

		return new ArrayList<ExportacaoDadosAlunoTO>();
		
	}
	
	

	
}
