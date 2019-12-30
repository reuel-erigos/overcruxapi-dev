package br.com.crux.cmd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.AcoesCompetenciaTOBuilder;
import br.com.crux.dao.repository.AcoesCompetenciaRepository;
import br.com.crux.entity.AcoesCompetencia;
import br.com.crux.exception.NotFoundException;
import br.com.crux.rule.CamposObrigatoriosAcoesCompentenciaRule;
import br.com.crux.to.AcoesCompetenciaTO;

@Component
public class AlterarAcoesCompetenciaCmd {

	@Autowired private AcoesCompetenciaRepository repository;
	@Autowired private CamposObrigatoriosAcoesCompentenciaRule camposObrigatoriosCargosRule;
	@Autowired private AcoesCompetenciaTOBuilder acoesCompetenciaTOBuilder;
	@Autowired private GetUsuarioLogadoCmd getUsuarioLogadoCmd;

	public void alterar(AcoesCompetenciaTO to) {
		AcoesCompetencia entity = repository.findById(to.getId()).orElseThrow(() -> new NotFoundException("Ação da competência do talento do funcionário informado não existe."));

		camposObrigatoriosCargosRule.verificar(to);

		to.setUsuarioAlteracao(getUsuarioLogadoCmd.getUsuarioLogado().getIdUsuario());

		entity = acoesCompetenciaTOBuilder.build(to);

		repository.save(entity);

	}
}
