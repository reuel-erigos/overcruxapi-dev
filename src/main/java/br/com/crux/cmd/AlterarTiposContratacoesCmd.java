package br.com.crux.cmd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.TiposContratacoesTOBuilder;
import br.com.crux.dao.repository.TiposContratacoesRepository;
import br.com.crux.entity.TiposContratacoes;
import br.com.crux.exception.NotFoundException;
import br.com.crux.rule.CamposObrigatoriosTiposContratacoesRule;
import br.com.crux.to.TiposContratacoesTO;

@Component
public class AlterarTiposContratacoesCmd {

	@Autowired private TiposContratacoesRepository repository;
	@Autowired private CamposObrigatoriosTiposContratacoesRule camposObrigatoriosRule;
	@Autowired private TiposContratacoesTOBuilder tiposContratacoesTOBuilder;

	public void alterar(TiposContratacoesTO to) {
		TiposContratacoes entity = repository.findById(to.getId()).orElseThrow(() -> new NotFoundException("Tipo contratação informado não existe."));

		camposObrigatoriosRule.verificar(to);

		entity = tiposContratacoesTOBuilder.build(to);

		repository.save(entity);

	}
}
