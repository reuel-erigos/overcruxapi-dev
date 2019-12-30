package br.com.crux.cmd;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.ResponsaveisAlunoTOBuilder;
import br.com.crux.dao.repository.ResponsaveisAlunoRepository;
import br.com.crux.entity.ResponsaveisAluno;
import br.com.crux.rule.CamposObrigatoriosResponsaveisAlunoRule;
import br.com.crux.to.FamiliaresTO;
import br.com.crux.to.ResponsaveisAlunoTO;

@Component
public class CadastrarResponsaveisAlunoCmd {

	@Autowired private ResponsaveisAlunoTOBuilder responsaveisAlunoTOBuilder;
	@Autowired private ResponsaveisAlunoRepository repository;
	@Autowired private CamposObrigatoriosResponsaveisAlunoRule camposObrigatoriosRule;
	@Autowired private GetUsuarioLogadoCmd getUsuarioLogadoCmd;

	public void cadastrar(List<ResponsaveisAlunoTO> responsaveisTO, FamiliaresTO familiarTO) {
		
		responsaveisTO.stream().forEach(respondavelTO -> {
			camposObrigatoriosRule.verificar(respondavelTO);
			
			respondavelTO.setUsuarioAlteracao(getUsuarioLogadoCmd.getUsuarioLogado().getIdUsuario());
			ResponsaveisAluno entity = responsaveisAlunoTOBuilder.build(respondavelTO, familiarTO);
			repository.save(entity);
		});
	
	}
}
