package br.com.crux.cmd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.AlunosTrabalhandoTOBuilder;
import br.com.crux.dao.repository.AlunosTrabalhandoRepository;
import br.com.crux.entity.AlunosTrabalhando;
import br.com.crux.rule.CamposObrigatoriosAlunosTrabalhandoRule;
import br.com.crux.to.AlunosTrabalhandoTO;

@Component
public class CadastrarAlunosTrabalhandoCmd {

	@Autowired private GetUsuarioLogadoCmd getUsuarioLogadoCmd;

	@Autowired private AlunosTrabalhandoRepository repository;
	@Autowired private CamposObrigatoriosAlunosTrabalhandoRule camposObrigatoriosRule;
	@Autowired private AlunosTrabalhandoTOBuilder alunosTrabalhandoTOBuilder;

	public void cadastrar(AlunosTrabalhandoTO to) {

		camposObrigatoriosRule.verificar(to);

		to.setUsuarioAlteracao(getUsuarioLogadoCmd.getUsuarioLogado().getIdUsuario());

		AlunosTrabalhando entity = alunosTrabalhandoTOBuilder.build(to);

		repository.save(entity);

	}
}
