package br.com.crux.cmd;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.AnexosAcaoPlanejamentoTOBuilder;
import br.com.crux.dao.repository.AnexosAcaoPlanejamentoRepository;
import br.com.crux.entity.AnexosAcaoPlanejamento;
import br.com.crux.to.AnexosAcaoPlanejamentoTO;

@Component
public class CadastrarAnexosAcaoPlanejamentoCmd {

	@Autowired private AnexosAcaoPlanejamentoRepository repository;
	@Autowired private AnexosAcaoPlanejamentoTOBuilder toBuilder;

	private void cadastrar(AnexosAcaoPlanejamentoTO to) {
		AnexosAcaoPlanejamento entity = toBuilder.build(to);
		repository.save(entity);
	}
	

	public void cadastrarAll(List<AnexosAcaoPlanejamentoTO> listaTO, Long idAcao) {
		listaTO.forEach(to -> {
			to.setIdAcao(idAcao);
			cadastrar(to); 
		});
	}
}
