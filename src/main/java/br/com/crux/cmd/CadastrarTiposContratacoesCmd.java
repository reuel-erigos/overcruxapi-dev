package br.com.crux.cmd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.TiposContratacoesTOBuilder;
import br.com.crux.dao.repository.TiposContratacoesRepository;
import br.com.crux.entity.TiposContratacoes;
import br.com.crux.rule.CamposObrigatoriosTiposContratacoesRule;
import br.com.crux.to.TiposContratacoesTO;

@Component
public class CadastrarTiposContratacoesCmd {

	@Autowired private TiposContratacoesRepository tiposContratacoesRepository;
	@Autowired private TiposContratacoesTOBuilder tiposContratacoesTOBuilder;
	@Autowired private CamposObrigatoriosTiposContratacoesRule camposObrigatoriosRule;

	public void cadastrar(TiposContratacoesTO to) {

		camposObrigatoriosRule.verificar(to);

		TiposContratacoes entity = tiposContratacoesTOBuilder.build(to);

		tiposContratacoesRepository.save(entity);

	}
}
