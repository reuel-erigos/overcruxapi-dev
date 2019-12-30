package br.com.crux.cmd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.OcorrenciaAlunoTOBuilder;
import br.com.crux.dao.repository.OcorrenciaAlunoRepository;
import br.com.crux.entity.OcorrenciaAluno;
import br.com.crux.rule.CamposObrigatoriosOcorrenciaAlunoRule;
import br.com.crux.to.OcorrenciaAlunoTO;

@Component
public class CadastrarOcorrenciaAlunoCmd {

	@Autowired private OcorrenciaAlunoRepository repository;
	@Autowired private CamposObrigatoriosOcorrenciaAlunoRule camposObrigatoriosRule;
	@Autowired private GetUsuarioLogadoCmd getUsuarioLogadoCmd;
	@Autowired private OcorrenciaAlunoTOBuilder tOBuilder;

	public void cadastrar(OcorrenciaAlunoTO to) {

		camposObrigatoriosRule.verificar(to);

		to.setUsuarioAlteracao(getUsuarioLogadoCmd.getUsuarioLogado().getIdUsuario());

		OcorrenciaAluno entity = tOBuilder.build(to);

		repository.save(entity);

	}
}
