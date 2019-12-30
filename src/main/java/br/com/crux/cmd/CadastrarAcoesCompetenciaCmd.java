package br.com.crux.cmd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.AcoesCompetenciaTOBuilder;
import br.com.crux.dao.repository.AcoesCompetenciaRepository;
import br.com.crux.entity.AcoesCompetencia;
import br.com.crux.rule.CamposObrigatoriosAcoesCompentenciaRule;
import br.com.crux.to.AcoesCompetenciaTO;

@Component
public class CadastrarAcoesCompetenciaCmd {

	@Autowired private AcoesCompetenciaRepository repository;
	@Autowired private CamposObrigatoriosAcoesCompentenciaRule camposObrigatoriosCargosRule;
	@Autowired private AcoesCompetenciaTOBuilder acoesCompetenciaTOBuilder;
	@Autowired private GetUsuarioLogadoCmd getUsuarioLogadoCmd;

	public void cadastrar(AcoesCompetenciaTO to) {

		camposObrigatoriosCargosRule.verificar(to);

		to.setUsuarioAlteracao(getUsuarioLogadoCmd.getUsuarioLogado().getIdUsuario());

		AcoesCompetencia entity = acoesCompetenciaTOBuilder.build(to);

		repository.save(entity);
	}
}
