package br.com.crux.cmd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.IndicadoresTOBuilder;
import br.com.crux.dao.repository.IndicadoresRepository;
import br.com.crux.entity.Indicadores;
import br.com.crux.exception.NotFoundException;
import br.com.crux.rule.CamposObrigatoriosIndicadoresRule;
import br.com.crux.to.IndicadoresTO;

@Component
public class AlterarIndicadoresCmd {

	@Autowired private IndicadoresRepository indicadoresRepository;
	@Autowired private GetUsuarioLogadoCmd getUsuarioLogadoCmd;
	@Autowired private CamposObrigatoriosIndicadoresRule camposObrigatoriosRule;
	@Autowired private IndicadoresTOBuilder indicadoresTOBuilder;

	public void alterar(IndicadoresTO to) {
		camposObrigatoriosRule.verificar(to);

		Indicadores entity = indicadoresRepository.findById(to.getIdIndicador()).orElseThrow(() -> new NotFoundException("Indicador informado não existe."));

		to.setUsuarioAlteracao(getUsuarioLogadoCmd.getUsuarioLogado().getIdUsuario());

		entity = indicadoresTOBuilder.build(to);

		indicadoresRepository.save(entity);

	}
}
