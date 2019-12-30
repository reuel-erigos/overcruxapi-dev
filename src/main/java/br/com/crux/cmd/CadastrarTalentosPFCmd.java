package br.com.crux.cmd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.TalentosPFTOBuilder;
import br.com.crux.dao.repository.TalentosPFRepository;
import br.com.crux.entity.TalentosPf;
import br.com.crux.rule.CamposObrigatoriosTalentosPFRule;
import br.com.crux.to.TalentosPfTO;

@Component
public class CadastrarTalentosPFCmd {

	@Autowired private TalentosPFRepository repository;
	@Autowired private TalentosPFTOBuilder talentosPFTOBuilder;
	@Autowired private CamposObrigatoriosTalentosPFRule camposObrigatoriosCargosRule;
	@Autowired private GetUsuarioLogadoCmd getUsuarioLogadoCmd;

	public void cadastrar(TalentosPfTO to) {

		camposObrigatoriosCargosRule.verificar(to);
		
		to.setUsuarioAlteracao(getUsuarioLogadoCmd.getUsuarioLogado().getIdUsuario());
		
		TalentosPf entity = talentosPFTOBuilder.build(to);

		repository.save(entity);

	}
}

