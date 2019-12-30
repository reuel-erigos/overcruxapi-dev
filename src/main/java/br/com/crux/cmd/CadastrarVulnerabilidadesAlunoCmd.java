package br.com.crux.cmd;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.VulnerabilidadesAlunoTOBuilder;
import br.com.crux.dao.repository.VulnerabilidadesAlunoRepository;
import br.com.crux.entity.VulnerabilidadesAluno;
import br.com.crux.rule.CamposObrigatoriosVulnerabilidadesAlunoRule;
import br.com.crux.to.AlunoTO;
import br.com.crux.to.VulnerabilidadesAlunoTO;

@Component
public class CadastrarVulnerabilidadesAlunoCmd {

	@Autowired private GetUsuarioLogadoCmd getUsuarioLogadoCmd;
	
	@Autowired private VulnerabilidadesAlunoTOBuilder vulnerabilidadesAlunoTOBuilder;
	@Autowired private VulnerabilidadesAlunoRepository repository;
	@Autowired private CamposObrigatoriosVulnerabilidadesAlunoRule camposObrigatoriosRule;
	
	
	public void cadastrar(List<VulnerabilidadesAlunoTO> vulnerabilidadesTO, AlunoTO alunoTO) {
		vulnerabilidadesTO.stream().forEach(vulnerabilidadeTO -> {
			camposObrigatoriosRule.verificar(vulnerabilidadeTO);
			
			vulnerabilidadeTO.setUsuarioAlteracao(getUsuarioLogadoCmd.getUsuarioLogado().getIdUsuario());
			VulnerabilidadesAluno entity = vulnerabilidadesAlunoTOBuilder.build(vulnerabilidadeTO, alunoTO);
			repository.save(entity);
		});
	
	}		
	
}
