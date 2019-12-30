package br.com.crux.cmd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.OcorrenciaAlunoTOBuilder;
import br.com.crux.dao.repository.OcorrenciaAlunoRepository;
import br.com.crux.entity.OcorrenciaAluno;
import br.com.crux.exception.NotFoundException;
import br.com.crux.rule.CamposObrigatoriosOcorrenciaAlunoRule;
import br.com.crux.to.OcorrenciaAlunoTO;

@Component
public class AlterarOcorrenciaAlunoCmd {

	@Autowired private OcorrenciaAlunoRepository repository;
	@Autowired private OcorrenciaAlunoTOBuilder tOBuilder;
	@Autowired private GetUsuarioLogadoCmd getUsuarioLogadoCmd;
	@Autowired private CamposObrigatoriosOcorrenciaAlunoRule camposObrigatoriosRule;

	public void alterar(OcorrenciaAlunoTO to) {
		OcorrenciaAluno entity = repository.findById(to.getId()).orElseThrow(() -> new NotFoundException("Ocorrẽncia informado não existe."));

		camposObrigatoriosRule.verificar(to);

		to.setUsuarioAlteracao(getUsuarioLogadoCmd.getUsuarioLogado().getIdUsuario());

		entity = tOBuilder.build(to);

		repository.save(entity);

	}
}
