package br.com.crux.cmd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.TipoOcorrenciaAlunoTOBuilder;
import br.com.crux.dao.repository.TipoOcorrenciaAlunoRepository;
import br.com.crux.entity.TipoOcorrenciaAluno;
import br.com.crux.rule.CamposObrigatoriosTipoOcorrenciaAlunoRule;
import br.com.crux.to.TipoOcorrenciaAlunoTO;

@Component
public class CadastrarTipoOcorrenciaAlunoCmd {

	@Autowired private TipoOcorrenciaAlunoRepository repository;
	@Autowired private CamposObrigatoriosTipoOcorrenciaAlunoRule camposObrigatoriosRule;
	@Autowired private GetUsuarioLogadoCmd getUsuarioLogadoCmd;
	@Autowired private TipoOcorrenciaAlunoTOBuilder tOBuilder;

	public void cadastrar(TipoOcorrenciaAlunoTO to) {

		camposObrigatoriosRule.verificar(to);

		to.setUsuarioAlteracao(getUsuarioLogadoCmd.getUsuarioLogado().getIdUsuario());

		TipoOcorrenciaAluno entity = tOBuilder.build(to);

		repository.save(entity);

	}
}
