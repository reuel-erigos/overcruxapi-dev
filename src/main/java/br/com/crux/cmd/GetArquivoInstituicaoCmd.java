package br.com.crux.cmd;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import br.com.crux.builder.ArquivoMetadadosTOBuilder;
import br.com.crux.dao.repository.ArquivoMetadadosRepository;
import br.com.crux.dao.repository.ArquivoRepository;
import br.com.crux.dao.spec.ArquivoMetadadoSpec;
import br.com.crux.entity.Arquivo;
import br.com.crux.entity.ArquivoMetadado;
import br.com.crux.entity.Instituicao;
import br.com.crux.exception.ParametroNaoInformadoException;
import br.com.crux.to.ArquivoMetadadoTO;
import br.com.crux.to.filtro.FiltroArquivoTO;

@Component
public class GetArquivoInstituicaoCmd {

	@Autowired private GetInstituicaoCmd getInstituicaoCmd;
	@Autowired private ArquivoRepository arquivoRepository;
	@Autowired private ArquivoMetadadosRepository arquivoMetadadosRepository;
	@Autowired private GetUnidadeLogadaCmd getUnidadeLogadaCmd;
	@Autowired private ArquivoMetadadosTOBuilder arquivoMetadadosTOBuilder;

	public byte[] get(Long idInstituicao) {
		if (Objects.isNull(idInstituicao)) {
			throw new ParametroNaoInformadoException("Erro ao buscar o arquivo, parâmetro não informado no resource.");
		}

		Instituicao instituicao = getInstituicaoCmd.getById(idInstituicao);

		if (instituicao.getMetadados() == null) {
			return null;
		}

		Optional<Arquivo> arquivo = arquivoRepository.findByIdMetadados(instituicao.getMetadados().getId());
		if (!arquivo.isPresent()) {
			return null;
		}

		return arquivo.get().getBlob();
	}

	public Page<ArquivoMetadadoTO> listFilteredAndPaged(FiltroArquivoTO filtro, Pageable pageable) {
		filtro.setIdInstituicao(getUnidadeLogadaCmd.getUnidadeTOSimplificado().getInstituicao().getId());
		Page<ArquivoMetadado> pageData = arquivoMetadadosRepository.findAll(ArquivoMetadadoSpec.findByCriteria(filtro), pageable);
		final List<ArquivoMetadadoTO> listTO = new ArrayList<ArquivoMetadadoTO>();
		pageData.getContent().forEach(item -> listTO.add(arquivoMetadadosTOBuilder.buildTO(item)));
		final Page<ArquivoMetadadoTO> pageDataTO = new PageImpl<ArquivoMetadadoTO>(listTO, pageable, pageData.getTotalElements());
		return pageDataTO;
	}
}
