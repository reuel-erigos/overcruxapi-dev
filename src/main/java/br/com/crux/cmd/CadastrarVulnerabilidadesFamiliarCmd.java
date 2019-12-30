package br.com.crux.cmd;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.VulnerabilidadesFamiliarTOBuilder;
import br.com.crux.dao.repository.VulnerabilidadesFamiliarRepository;
import br.com.crux.entity.VulnerabilidadesFamiliar;
import br.com.crux.rule.CamposObrigatoriosVulnerabilidadesFamiliarRule;
import br.com.crux.to.FamiliaresTO;
import br.com.crux.to.VulnerabilidadesFamiliarTO;

@Component
public class CadastrarVulnerabilidadesFamiliarCmd {

	@Autowired private GetUsuarioLogadoCmd getUsuarioLogadoCmd;
	
	@Autowired private VulnerabilidadesFamiliarRepository repository;
	@Autowired private CamposObrigatoriosVulnerabilidadesFamiliarRule camposObrigatoriosRule;
	@Autowired private VulnerabilidadesFamiliarTOBuilder vulnerabilidadesFamiliarTOBuilder;

	
	public void cadastrar(List<VulnerabilidadesFamiliarTO> vulnerabilidadesTO, FamiliaresTO familiarTO) {
		
		vulnerabilidadesTO.stream().forEach(vulnerabilidadeTO -> {
			camposObrigatoriosRule.verificar(vulnerabilidadeTO);
			
			vulnerabilidadeTO.setUsuarioAlteracao(getUsuarioLogadoCmd.getUsuarioLogado().getIdUsuario());
			VulnerabilidadesFamiliar entity = vulnerabilidadesFamiliarTOBuilder.build(vulnerabilidadeTO, familiarTO);
			repository.save(entity);
		});
	
	}	
}
