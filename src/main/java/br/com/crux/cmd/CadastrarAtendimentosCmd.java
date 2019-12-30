package br.com.crux.cmd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.AtendimentosTOBuilder;
import br.com.crux.dao.repository.AtendimentosRepository;
import br.com.crux.entity.Atendimentos;
import br.com.crux.rule.CamposObrigatoriosAtendimentosRule;
import br.com.crux.to.AtendimentosTO;

@Component
public class CadastrarAtendimentosCmd {

	@Autowired private GetUsuarioLogadoCmd getUsuarioLogadoCmd;
	@Autowired private AtendimentosRepository repository;
	@Autowired private CamposObrigatoriosAtendimentosRule camposObrigatoriosRule;
	@Autowired private AtendimentosTOBuilder atendimentosTOBuilder;

	public void cadastrar(AtendimentosTO to) {

		camposObrigatoriosRule.verificar(to);

		to.setUsuarioAlteracao(getUsuarioLogadoCmd.getUsuarioLogado().getIdUsuario());

		Atendimentos entity = atendimentosTOBuilder.build(to);

		repository.save(entity);

	}
}
