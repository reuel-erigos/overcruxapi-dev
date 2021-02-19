package br.com.crux.cmd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.GrupoAcoesTOBuilder;
import br.com.crux.dao.repository.GrupoAcoesRepository;
import br.com.crux.entity.GrupoAcoes;
import br.com.crux.rule.CamposObrigatoriosGrupoAcoesRule;
import br.com.crux.to.GrupoAcoesTO;

@Component
public class CadastrarGrupoAcoesCmd {

	@Autowired private GetUsuarioLogadoCmd getUsuarioLogadoCmd;
	@Autowired private GrupoAcoesRepository repository;
	@Autowired private GrupoAcoesTOBuilder toBuilder;
	@Autowired private CamposObrigatoriosGrupoAcoesRule camposObrigatoriosRule;

	public GrupoAcoesTO cadastrar(GrupoAcoesTO to) {
		camposObrigatoriosRule.verificar(to);
		
		to.setUsuarioAlteracao(getUsuarioLogadoCmd.getUsuarioLogado().getIdUsuario());
		GrupoAcoes entity = toBuilder.build(to);
		
		entity = repository.save(entity);
		return toBuilder.buildTO(entity);
		
	}
	
}
