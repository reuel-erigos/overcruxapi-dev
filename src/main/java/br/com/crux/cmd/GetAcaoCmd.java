package br.com.crux.cmd;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.AcaoTOBuilder;
import br.com.crux.dao.repository.AcaoRepository;
import br.com.crux.entity.Acoes;
import br.com.crux.exception.NotFoundException;
import br.com.crux.to.AcaoTO;

@Component
public class GetAcaoCmd {

	@Autowired
	private AcaoRepository repository;
	@Autowired
	private AcaoTOBuilder toBuilder;
	@Autowired
	private GetUnidadeLogadaCmd getUnidadeLogadaCmd;

	public List<AcaoTO> getAllFilter(Long idUnidade, Long idTurma, Long idOficina, Long idAcao, String statusAnalise) {

		Optional<List<Acoes>> entitys = Optional.empty();

		idUnidade     = Objects.isNull(idUnidade) ? null : idUnidade;
		idTurma       = Objects.isNull(idTurma) ? null : idTurma;
		idOficina     = Objects.isNull(idOficina) ? null : idOficina;
		idAcao        = Objects.isNull(idAcao) ? null : idAcao;
		statusAnalise = StringUtils.isBlank(statusAnalise) ? null : statusAnalise;

		Long idInstituicao = getUnidadeLogadaCmd.getUnidadeTO().getInstituicao().getId();
		
		entitys = repository.findByFilters(idInstituicao, idUnidade, idTurma, idOficina, idAcao, statusAnalise);
		
		if (entitys.isPresent()) {
			return toBuilder.buildAll(entitys.get());
		}

		return new ArrayList<AcaoTO>();
	}

	public List<AcaoTO> getAllByInstituicao() {
		Long idInstituicao = getUnidadeLogadaCmd.getUnidadeTO().getInstituicao().getId();
		Optional<List<Acoes>> entitys = repository.findByInstituicao(idInstituicao);
		if (entitys.isPresent()) {
			return toBuilder.buildAll(entitys.get());
		}
		return new ArrayList<AcaoTO>();
	}

	public AcaoTO getByTOId(Long id) {
		Acoes entity = repository.findById(id).orElseThrow(() -> new NotFoundException("Acao não encontrada."));
		return toBuilder.buildTO(entity);
	}

	public Acoes getById(Long id) {
		return repository.findById(id).orElse(null);
	}

}
