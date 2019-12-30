package br.com.crux.cmd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.ReprovacoesAlunoTOBuilder;
import br.com.crux.dao.repository.ReprovacoesAlunoRepository;
import br.com.crux.entity.ReprovacoesAluno;
import br.com.crux.rule.CamposObrigatoriosReprovacoesAlunoRule;
import br.com.crux.to.ReprovacoesAlunoTO;

@Component
public class CadastrarReprovacoesAlunoCmd {

	@Autowired private GetUsuarioLogadoCmd getUsuarioLogadoCmd;
	@Autowired private ReprovacoesAlunoTOBuilder reprovacoesAlunoTOBuilder;
	@Autowired private ReprovacoesAlunoRepository repository;
	@Autowired private CamposObrigatoriosReprovacoesAlunoRule camposObrigatoriosRule;
	
	
	public void cadastrar(ReprovacoesAlunoTO to) {
		camposObrigatoriosRule.verificar(to);
		ReprovacoesAluno entity = reprovacoesAlunoTOBuilder.build(to);
		entity.setUsuarioAlteracao(getUsuarioLogadoCmd.getUsuarioLogado().getIdUsuario());
		repository.save(entity);
	}
}
