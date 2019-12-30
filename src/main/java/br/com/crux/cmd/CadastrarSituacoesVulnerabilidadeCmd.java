package br.com.crux.cmd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.SituacoesVulnerabilidadeTOBuilder;
import br.com.crux.dao.repository.SituacoesVulnerabilidadeRepository;
import br.com.crux.entity.SituacoesVulnerabilidade;
import br.com.crux.rule.CamposObrigatoriosSituacoesVulnerabilidadeRule;
import br.com.crux.to.SituacoesVulnerabilidadeTO;

@Component
public class CadastrarSituacoesVulnerabilidadeCmd {

	@Autowired private GetUsuarioLogadoCmd getUsuarioLogadoCmd;
	
	@Autowired private SituacoesVulnerabilidadeRepository repository;
	@Autowired private CamposObrigatoriosSituacoesVulnerabilidadeRule camposObrigatoriosRule;
	@Autowired private SituacoesVulnerabilidadeTOBuilder toBuilder;
	
	
	public void cadastrar(SituacoesVulnerabilidadeTO to) {
		camposObrigatoriosRule.verificar(to.getDescricao());
		
		SituacoesVulnerabilidade entity = new SituacoesVulnerabilidade();
		entity = toBuilder.build(to);
		entity.setUsuarioAlteracao(getUsuarioLogadoCmd.getUsuarioLogado().getIdUsuario());
		
		repository.save(entity);
		
	}
}
