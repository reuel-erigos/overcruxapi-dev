package br.com.crux.cmd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.AlunosTrabalhandoTOBuilder;
import br.com.crux.dao.repository.AlunosTrabalhandoRepository;
import br.com.crux.entity.AlunosTrabalhando;
import br.com.crux.exception.NotFoundException;
import br.com.crux.rule.CamposObrigatoriosAlunosTrabalhandoRule;
import br.com.crux.to.AlunosTrabalhandoTO;

@Component
public class AlterarAlunosTrabalhandoCmd {

	@Autowired private GetUsuarioLogadoCmd getUsuarioLogadoCmd;

	@Autowired private AlunosTrabalhandoRepository repository;
	@Autowired private CamposObrigatoriosAlunosTrabalhandoRule camposObrigatoriosRule;
	@Autowired private AlunosTrabalhandoTOBuilder alunosTrabalhandoTOBuilder;

	public void alterar(AlunosTrabalhandoTO to) {
		
		AlunosTrabalhando entity = repository.findById(to.getId()).orElseThrow(() -> new NotFoundException("Trabalho aluno não existe."));

		camposObrigatoriosRule.verificar(to);

		to.setUsuarioAlteracao(getUsuarioLogadoCmd.getUsuarioLogado().getIdUsuario());

		entity = alunosTrabalhandoTOBuilder.build(to);

		repository.save(entity);
	}
}
