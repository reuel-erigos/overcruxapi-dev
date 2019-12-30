package br.com.crux.cmd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.TurmasTOBuilder;
import br.com.crux.dao.repository.TurmasRepository;
import br.com.crux.entity.Turmas;
import br.com.crux.rule.CamposObrigatoriosTurmasRule;
import br.com.crux.to.TurmasTO;

@Component
public class CadastrarTurmasCmd {

	@Autowired private TurmasRepository repository;
	@Autowired private TurmasTOBuilder toBuilder;
	@Autowired private CamposObrigatoriosTurmasRule camposObrigatoriosRule;
	
	@Autowired private GetUsuarioLogadoCmd getUsuarioLogadoCmd;
	@Autowired private CadastrarColaboradoresTurmaCmd cadastrarColaboradoresTurmaCmd;
	@Autowired private CadastrarAtividadesCmd cadastrarAtividadesCmd;

	public void cadastrar(TurmasTO to) {

		camposObrigatoriosRule.verificar(to);
		to.setUsuarioAlteracao(getUsuarioLogadoCmd.getUsuarioLogado().getIdUsuario());

		Turmas turma = repository.save(toBuilder.build(to));
		TurmasTO turmaTO = toBuilder.buildTO(turma);
		
		cadastrarColaboradoresTurmaCmd.cadastrarAll(to.getColaboradores(), turmaTO);
		cadastrarAtividadesCmd.cadastrarAll(to.getOficinas(), turmaTO);

	}
}
