package br.com.crux.cmd;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.AlunosTurmaTOBuilder;
import br.com.crux.dao.repository.AlunosTurmaRepository;
import br.com.crux.entity.AlunosTurma;
import br.com.crux.rule.CamposObrigatoriosAlunosTurmaRule;
import br.com.crux.to.AlunosTurmaTO;

@Component
public class CadastrarAlunosTurmaCmd {

	@Autowired private GetUsuarioLogadoCmd getUsuarioLogadoCmd;

	@Autowired private AlunosTurmaRepository repository;
	@Autowired private CamposObrigatoriosAlunosTurmaRule camposObrigatoriosRule;
	@Autowired private AlunosTurmaTOBuilder atividadesAlunoTOBuilder;
	@Autowired private CadastrarAtividadesAlunoCmd cadastrarAtividadesAlunoCmd;

	public void cadastrar(AlunosTurmaTO to) {
		camposObrigatoriosRule.verificar(to);

		to.setUsuarioAlteracao(getUsuarioLogadoCmd.getUsuarioLogado().getIdUsuario());
		AlunosTurma entity = atividadesAlunoTOBuilder.build(to);
		entity.setDataCadastro(LocalDateTime.now());

		repository.save(entity);
		cadastrarAtividadesAlunoCmd.cadastrarAll(to.getOficinas());
	}
}
