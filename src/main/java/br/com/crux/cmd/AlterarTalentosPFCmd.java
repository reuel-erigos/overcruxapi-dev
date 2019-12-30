package br.com.crux.cmd;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.TalentosPFTOBuilder;
import br.com.crux.dao.repository.TalentosPFRepository;
import br.com.crux.entity.TalentosPf;
import br.com.crux.exception.NotFoundException;
import br.com.crux.rule.CamposObrigatoriosTalentosPFRule;
import br.com.crux.to.TalentosPfTO;

@Component
public class AlterarTalentosPFCmd {

	@Autowired private TalentosPFRepository repository;
	@Autowired private GetTalentosPFCmd getTalentosPFCmd;
	@Autowired private TalentosPFTOBuilder talentosPFTOBuilder;
	@Autowired private CamposObrigatoriosTalentosPFRule camposObrigatoriosCargosRule;
	@Autowired private GetUsuarioLogadoCmd getUsuarioLogadoCmd;

	public void alterar(TalentosPfTO to) {

		camposObrigatoriosCargosRule.verificar(to);

		TalentosPf entity = Optional.ofNullable(getTalentosPFCmd.getById(to.getId())).orElseThrow(() -> new NotFoundException("Talentos do Funcionario não encontrado."));

		to.setUsuarioAlteracao(getUsuarioLogadoCmd.getUsuarioLogado().getIdUsuario());

		entity = talentosPFTOBuilder.build(to);

		repository.save(entity);

	}
}
