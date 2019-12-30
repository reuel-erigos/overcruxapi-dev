package br.com.crux.cmd;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.AlocacoesFuncionarioTOBuilder;
import br.com.crux.dao.repository.AlocacaoFuncionarioRepository;
import br.com.crux.entity.AlocacoesFuncionario;
import br.com.crux.rule.CamposObrigatoriosAlocacoesFuncionarioRule;
import br.com.crux.to.AlocacoesFuncionarioTO;
import br.com.crux.to.FuncionarioTO;

@Component
public class CadastrarAlocacoesFuncionarioCmd {

	@Autowired private AlocacoesFuncionarioTOBuilder toBuilder;
	@Autowired private AlocacaoFuncionarioRepository repository;
	@Autowired private CamposObrigatoriosAlocacoesFuncionarioRule camposObrigatoriosRule;
	@Autowired private GetUsuarioLogadoCmd getUsuarioLogadoCmd;

	public void cadastrar(List<AlocacoesFuncionarioTO> alocacoesFuncionarioTO, FuncionarioTO funcionarioTO) {
		
		alocacoesFuncionarioTO.stream().forEach(alocacaoTO -> {
			camposObrigatoriosRule.verificar(alocacaoTO);
			
			alocacaoTO.setUsuarioAlteracao(getUsuarioLogadoCmd.getUsuarioLogado().getIdUsuario());
			
			alocacaoTO.getFuncionario().setId(funcionarioTO.getId());
			AlocacoesFuncionario entity = toBuilder.build(alocacaoTO);
			
			repository.save(entity);
		});
	
	}
}
