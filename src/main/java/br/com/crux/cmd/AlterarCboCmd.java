package br.com.crux.cmd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.CboTOBuilder;
import br.com.crux.dao.repository.CboRepository;
import br.com.crux.entity.Cbo;
import br.com.crux.exception.NotFoundException;
import br.com.crux.rule.CamposObrigatoriosCboRule;
import br.com.crux.to.CboTO;

@Component
public class AlterarCboCmd {

	@Autowired private CboRepository repository;
	@Autowired private GetUsuarioLogadoCmd getUsuarioLogadoCmd;
	@Autowired private CboTOBuilder tOBuilder;
	@Autowired private CamposObrigatoriosCboRule camposObrigatoriosRule;

	public void alterar(CboTO to) {
		Cbo entity = repository.findById(to.getId()).orElseThrow(() -> new NotFoundException("CBO informado não existe."));

		camposObrigatoriosRule.verificar(to);

		to.setUsuarioAlteracao(getUsuarioLogadoCmd.getUsuarioLogado().getIdUsuario());

		entity = tOBuilder.build(to);

		repository.save(entity);

	}
}
