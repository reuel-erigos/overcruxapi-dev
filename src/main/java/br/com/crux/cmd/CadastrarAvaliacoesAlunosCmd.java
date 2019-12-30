package br.com.crux.cmd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.AvaliacoesAlunosTOBuilder;
import br.com.crux.dao.repository.AvaliacoesAlunosRepository;
import br.com.crux.entity.AvaliacoesAlunos;
import br.com.crux.rule.CamposObrigatoriosAvaliacoesAlunosRule;
import br.com.crux.to.AvaliacoesAlunosTO;

@Component
public class CadastrarAvaliacoesAlunosCmd {

	@Autowired private GetUsuarioLogadoCmd getUsuarioLogadoCmd;

	@Autowired private AvaliacoesAlunosRepository repository;
	@Autowired private CamposObrigatoriosAvaliacoesAlunosRule camposObrigatoriosRule;
	@Autowired private AvaliacoesAlunosTOBuilder avaliacoesAlunosTOBuilder;

	public void cadastrar(AvaliacoesAlunosTO to) {

		camposObrigatoriosRule.verificar(to);

		to.setUsuarioAlteracao(getUsuarioLogadoCmd.getUsuarioLogado().getIdUsuario());

		AvaliacoesAlunos entity = avaliacoesAlunosTOBuilder.build(to);

		repository.save(entity);

	}
}
