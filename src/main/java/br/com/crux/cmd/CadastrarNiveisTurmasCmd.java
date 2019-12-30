package br.com.crux.cmd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.NiveisTurmasTOBuilder;
import br.com.crux.dao.repository.NiveisTurmasRepository;
import br.com.crux.entity.NiveisTurmas;
import br.com.crux.rule.CamposObrigatoriosNiveisTurmasRule;
import br.com.crux.to.NiveisTurmasTO;

@Component
public class CadastrarNiveisTurmasCmd {

	@Autowired private NiveisTurmasRepository repository;
	@Autowired private CamposObrigatoriosNiveisTurmasRule camposObrigatoriosRule;
	@Autowired private NiveisTurmasTOBuilder toBuilder;
	@Autowired private GetUsuarioLogadoCmd getUsuarioLogadoCmd;

	public void cadastrar(NiveisTurmasTO to) {
		camposObrigatoriosRule.verificar(to);
		to.setUsuarioAlteracao(getUsuarioLogadoCmd.getUsuarioLogado().getIdUsuario());
		
		NiveisTurmas entity = toBuilder.build(to);
		repository.save(entity);

	}	
	
}
