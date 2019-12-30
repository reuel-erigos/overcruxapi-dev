package br.com.crux.cmd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.IndicadoresTOBuilder;
import br.com.crux.dao.repository.IndicadoresRepository;
import br.com.crux.entity.Indicadores;
import br.com.crux.rule.CamposObrigatoriosIndicadoresRule;
import br.com.crux.to.IndicadoresTO;

@Component
public class CadastrarIndicadoresCmd {

	@Autowired private IndicadoresRepository indicadoresRepository;
	@Autowired private CamposObrigatoriosIndicadoresRule camposObrigatoriosRule;
	@Autowired private GetUsuarioLogadoCmd getUsuarioLogadoCmd;
	@Autowired private IndicadoresTOBuilder indicadoresTOBuilder;

	public void cadastrar(IndicadoresTO to) {

		camposObrigatoriosRule.verificar(to);

		to.setUsuarioAlteracao(getUsuarioLogadoCmd.getUsuarioLogado().getIdUsuario());

		Indicadores entity = indicadoresTOBuilder.build(to);

		indicadoresRepository.save(entity);

	}
}
