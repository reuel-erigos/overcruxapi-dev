package br.com.crux.cmd;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import br.com.crux.builder.EscolaTOBuilder;
import br.com.crux.dao.repository.EscolaRepository;
import br.com.crux.dao.spec.EscolaSpec;
import br.com.crux.entity.Escola;
import br.com.crux.exception.NotFoundException;
import br.com.crux.to.EscolaTO;
import br.com.crux.to.filtro.FiltroEscolaTO;

@Component
public class GetEscolaCmd {

	@Autowired private EscolaRepository repository;
	@Autowired private EscolaTOBuilder toBuilder;
	@Autowired private GetUnidadeLogadaCmd getUnidadeLogadaCmd;
	
	public List<EscolaTO> getAllEscolasByCombo(String tipo) {
		Long idInstituicao = getUnidadeLogadaCmd.getUnidadeTOSimplificado().getInstituicao().getId();
		List<Escola> escolas = repository.findByInstituicaoIdAndTipoOrderByNome(idInstituicao, tipo);
		return toBuilder.buildAllCombo(escolas);
	}

	public EscolaTO getTOById(Long id) {
		Escola entity = repository.findById(id)
				.orElseThrow(() -> new NotFoundException("Escola não encontrada."));
		return toBuilder.buildTO(entity);
	}

	public Page<EscolaTO> listFilteredAndPaged(FiltroEscolaTO filtro, Pageable pageable) {
		Long idInstituicao = getUnidadeLogadaCmd.getUnidadeTOSimplificado().getInstituicao().getId();
		filtro.setIdInstituicao(idInstituicao);
		Page<Escola> pageData = repository.findAll(EscolaSpec.findByCriteria(filtro), pageable);
		final List<EscolaTO> listTO = new ArrayList<EscolaTO>();
		pageData.getContent().forEach(regiao -> listTO.add(toBuilder.buildTO(regiao)));
		final Page<EscolaTO> pageDataTO = new PageImpl<EscolaTO>(listTO, pageable, pageData.getTotalElements());
		return pageDataTO;
	}



}
