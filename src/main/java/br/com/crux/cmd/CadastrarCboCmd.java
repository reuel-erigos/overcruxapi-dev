package br.com.crux.cmd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.CboTOBuilder;
import br.com.crux.dao.repository.CboRepository;
import br.com.crux.entity.Cbo;
import br.com.crux.rule.CamposObrigatoriosCboRule;
import br.com.crux.to.CboTO;

@Component
public class CadastrarCboCmd {

	@Autowired private CboRepository repository;
	@Autowired private CamposObrigatoriosCboRule camposObrigatoriosRule;
	@Autowired private GetUsuarioLogadoCmd getUsuarioLogadoCmd;
	@Autowired private CboTOBuilder tOBuilder;

	public void cadastrar(CboTO to) {

		camposObrigatoriosRule.verificar(to);

		to.setUsuarioAlteracao(getUsuarioLogadoCmd.getUsuarioLogado().getIdUsuario());

		Cbo entity = tOBuilder.build(to);

		repository.save(entity);

	}
}
