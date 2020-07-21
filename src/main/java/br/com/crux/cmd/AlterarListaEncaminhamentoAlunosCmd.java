package br.com.crux.cmd;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.EncaminhaAlunosTOBuilder;
import br.com.crux.dao.repository.EncaminhaAlunosRepository;
import br.com.crux.entity.Aluno;
import br.com.crux.entity.EncaminhaAlunos;
import br.com.crux.to.EncaminhaAlunosTO;

@Component
public class AlterarListaEncaminhamentoAlunosCmd extends AbstractAlterarListaCmd<EncaminhaAlunos, EncaminhaAlunosTO, Aluno> {

	@Autowired private EncaminhaAlunosTOBuilder toBuilder;
	@Autowired private EncaminhaAlunosRepository repository;
	@Autowired private CadastrarEncaminhaAlunosCmd cadastrarCmd;
	@Autowired private GetEncaminhaAlunosCmd getCmd;

	
	@Override
	protected EncaminhaAlunosTO getTO(EncaminhaAlunos entity) {
		return toBuilder.buildTO(entity);
	}

	@Override
	protected List<EncaminhaAlunosTO> getTOListaBanco(List<EncaminhaAlunos> lista) {
		return toBuilder.buildAllTO(lista);
	}

	@Override
	protected List<EncaminhaAlunos> getListaBanco(Aluno pai) {
		return getCmd.getAllEntity(pai.getId(), null);
	}

	@Override
	protected Long getIdentificadorTO(EncaminhaAlunosTO to) {
		return to.getId();
	}

	@Override
	protected void cadastrar(EncaminhaAlunosTO to, Aluno p) {
		cadastrarCmd.cadastrar(p.getId(), to);
	}

	@Override
	protected void deletar(EncaminhaAlunos registro) {
		repository.delete(registro);

	}

}
