package br.com.crux.cmd;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.ColaboradoresTurmaTOBuilder;
import br.com.crux.dao.repository.ColaboradoresTurmaRepository;
import br.com.crux.entity.ColaboradoresTurma;
import br.com.crux.rule.CamposObrigatoriosColaboradoresTurmaRule;
import br.com.crux.to.ColaboradoresTurmaTO;
import br.com.crux.to.TurmasTO;

@Component
public class CadastrarColaboradoresTurmaCmd {

	@Autowired private ColaboradoresTurmaRepository repository;
	@Autowired private ColaboradoresTurmaTOBuilder toBuilder;
	@Autowired private CamposObrigatoriosColaboradoresTurmaRule camposObrigatoriosRule;
	@Autowired private GetUsuarioLogadoCmd getUsuarioLogadoCmd;

	public void cadastrarAll(List<ColaboradoresTurmaTO> colaboradoresTO, TurmasTO turmaTO) {
		
		colaboradoresTO.stream().forEach(colaboradoreTO -> {
			camposObrigatoriosRule.verificar(colaboradoreTO);
			colaboradoreTO.setUsuarioAlteracao(getUsuarioLogadoCmd.getUsuarioLogado().getIdUsuario());
			colaboradoreTO.setIdTurma(turmaTO.getId());
			ColaboradoresTurma entity = toBuilder.build(colaboradoreTO);
			repository.save(entity);
		});
	
	}

}
