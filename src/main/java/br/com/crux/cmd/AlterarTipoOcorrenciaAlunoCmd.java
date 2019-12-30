package br.com.crux.cmd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.TipoOcorrenciaAlunoTOBuilder;
import br.com.crux.dao.repository.TipoOcorrenciaAlunoRepository;
import br.com.crux.entity.TipoOcorrenciaAluno;
import br.com.crux.exception.NotFoundException;
import br.com.crux.rule.CamposObrigatoriosTipoOcorrenciaAlunoRule;
import br.com.crux.to.TipoOcorrenciaAlunoTO;

@Component
public class AlterarTipoOcorrenciaAlunoCmd {

	@Autowired private TipoOcorrenciaAlunoRepository repository;
	@Autowired private TipoOcorrenciaAlunoTOBuilder tOBuilder;
	@Autowired private GetUsuarioLogadoCmd getUsuarioLogadoCmd;
	@Autowired private CamposObrigatoriosTipoOcorrenciaAlunoRule camposObrigatoriosRule;

	public void alterar(TipoOcorrenciaAlunoTO to) {
		TipoOcorrenciaAluno entity = repository.findById(to.getId()).orElseThrow(() -> new NotFoundException("Tipo de ocorrẽncia informado não existe."));

		camposObrigatoriosRule.verificar(to);

		to.setUsuarioAlteracao(getUsuarioLogadoCmd.getUsuarioLogado().getIdUsuario());

		entity = tOBuilder.build(to);

		repository.save(entity);

	}
}
