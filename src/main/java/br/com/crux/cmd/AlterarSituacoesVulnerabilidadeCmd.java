package br.com.crux.cmd;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.SituacoesVulnerabilidadeTOBuilder;
import br.com.crux.dao.repository.SituacoesVulnerabilidadeRepository;
import br.com.crux.entity.SituacoesVulnerabilidade;
import br.com.crux.exception.NotFoundException;
import br.com.crux.rule.CamposObrigatoriosSituacoesVulnerabilidadeRule;
import br.com.crux.to.SituacoesVulnerabilidadeTO;

@Component
public class AlterarSituacoesVulnerabilidadeCmd {

	@Autowired private GetUsuarioLogadoCmd getUsuarioLogadoCmd;
	
	@Autowired private SituacoesVulnerabilidadeRepository repository;
	@Autowired private CamposObrigatoriosSituacoesVulnerabilidadeRule camposObrigatoriosRule;
	@Autowired private SituacoesVulnerabilidadeTOBuilder toBuilder;
	
	
	public void alterar(SituacoesVulnerabilidadeTO to) {
		Optional<SituacoesVulnerabilidade> entityOptional = repository.findById(to.getId());
		if(!entityOptional.isPresent()) {
			throw new NotFoundException("Situacoes de vulnerabilidade informada não existe.");
		}
		
		camposObrigatoriosRule.verificar(to.getDescricao());
		
		SituacoesVulnerabilidade entity = entityOptional.get();
		entity = toBuilder.build(to);
		entity.setUsuarioAlteracao(getUsuarioLogadoCmd.getUsuarioLogado().getIdUsuario());
		
		repository.save(entity);
		
	}
}
