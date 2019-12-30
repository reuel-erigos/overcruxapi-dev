package br.com.crux.cmd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.AtendimentosTOBuilder;
import br.com.crux.dao.repository.AtendimentosRepository;
import br.com.crux.entity.Atendimentos;
import br.com.crux.exception.NotFoundException;
import br.com.crux.rule.CamposObrigatoriosAtendimentosRule;
import br.com.crux.to.AtendimentosTO;

@Component
public class AlterarAtendimentosCmd {

	@Autowired private GetUsuarioLogadoCmd getUsuarioLogadoCmd;
	@Autowired private AtendimentosRepository repository;
	@Autowired private CamposObrigatoriosAtendimentosRule camposObrigatoriosRule;
	@Autowired private AtendimentosTOBuilder atendimentosTOBuilder;

	public void alterar(AtendimentosTO to) {
		Atendimentos entity = repository.findById(to.getId()).orElseThrow(() -> new NotFoundException("Atendimento informado não existe."));

		camposObrigatoriosRule.verificar(to);

		to.setUsuarioAlteracao(getUsuarioLogadoCmd.getUsuarioLogado().getIdUsuario());

		entity = atendimentosTOBuilder.build(to);

		repository.save(entity);

	}
}
