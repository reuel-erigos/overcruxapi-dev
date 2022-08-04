package br.com.crux.cmd;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.EscolaTOBuilder;
import br.com.crux.dao.repository.EscolaRepository;
import br.com.crux.entity.Escola;
import br.com.crux.to.EscolaTO;

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


}
