package br.com.crux.cmd;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.GrupoAcoesTOBuilder;
import br.com.crux.dao.repository.GrupoAcoesRepository;
import br.com.crux.entity.GrupoAcoes;
import br.com.crux.to.GrupoAcoesTO;

@Component
public class GetGrupoAcoesCmd {

	@Autowired private GrupoAcoesRepository repository;
	@Autowired private GrupoAcoesTOBuilder toBuilder;
	@Autowired private GetUnidadeLogadaCmd getUnidadeLogadaCmd;

	public GrupoAcoesTO getByNumeroAndAtividade(String numero, Long idAtividade) {
		Optional<GrupoAcoes> entity = repository.findByNumeroAndAtividade(numero, idAtividade);
		if(entity.isPresent()) {
			return toBuilder.buildTO(entity.get());
		}
		return null;
	}
	
	public GrupoAcoesTO getByTOId(Long id) {
		Optional<GrupoAcoes> entity = repository.findById(id);
		if(entity.isPresent()) {
			return toBuilder.buildTO(entity.get());
		}
		return null;
	}

	public GrupoAcoes getById(Long id) {
		return repository.findById(id).orElse(null);
	}

	public List<GrupoAcoesTO> findByFilters(Long idUnidade, Long idTurma, Long idOficina, Long idAcao, String statusAnalise) {
		idUnidade = Objects.isNull(idUnidade) ? null : idUnidade;
		idTurma = Objects.isNull(idTurma) ? null : idTurma;
		idOficina = Objects.isNull(idOficina) ? null : idOficina;
		idAcao = Objects.isNull(idAcao) ? null : idAcao;
		statusAnalise = StringUtils.isBlank(statusAnalise) ? null : statusAnalise;

		Long idInstituicao = getUnidadeLogadaCmd.getUnidadeTO().getInstituicao().getId();

		Optional<List<GrupoAcoes>> entitys = repository.findByFilters(idInstituicao, idUnidade, idTurma, idOficina, idAcao,
				statusAnalise);

		if (entitys.isPresent())
			return toBuilder.buildAll(entitys.get());

		return new ArrayList<>();
	}
}
