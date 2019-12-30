package br.com.crux.cmd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.UniformesAlunoTOBuilder;
import br.com.crux.dao.repository.UniformesAlunoRepository;
import br.com.crux.entity.UniformesAluno;
import br.com.crux.rule.CamposObrigatoriosUniformesAlunoRule;
import br.com.crux.to.UniformesAlunoTO;

@Component
public class CadastrarUniformesAlunoCmd {

	@Autowired private GetUsuarioLogadoCmd getUsuarioLogadoCmd;

	@Autowired private UniformesAlunoRepository repository;
	@Autowired private CamposObrigatoriosUniformesAlunoRule camposObrigatoriosRule;
	@Autowired private UniformesAlunoTOBuilder uniformesAlunoTOBuilder;

	
	public void cadastrar(UniformesAlunoTO to) {
		camposObrigatoriosRule.verificar(to);
		
		UniformesAluno entity = uniformesAlunoTOBuilder.build(to);
		entity.setUsuarioAlteracao(getUsuarioLogadoCmd.getUsuarioLogado().getIdUsuario());
		
		repository.save(entity);
		
	}
}
