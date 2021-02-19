package br.com.crux.cmd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.GrupoAcoesTOBuilder;
import br.com.crux.dao.repository.GrupoAcoesRepository;
import br.com.crux.entity.GrupoAcoes;
import br.com.crux.rule.CamposObrigatoriosGrupoAcoesRule;
import br.com.crux.to.GrupoAcoesTO;

@Component
public class AlterarGrupoAcoesCmd {

	@Autowired private GetUsuarioLogadoCmd getUsuarioLogadoCmd;

	@Autowired private GrupoAcoesRepository repository;
	@Autowired private CamposObrigatoriosGrupoAcoesRule camposObrigatoriosRule;
	@Autowired private GrupoAcoesTOBuilder toBuilder;
	
	public GrupoAcoesTO alterar(GrupoAcoesTO to) {
		camposObrigatoriosRule.verificar(to);
		GrupoAcoes entity = repository.findById(to.getId()).orElse(null);
		to.setUsuarioAlteracao(getUsuarioLogadoCmd.getUsuarioLogado().getIdUsuario());

		entity = repository.save(toBuilder.build(to));
		return toBuilder.buildTO(entity);
	}
	
	
}
