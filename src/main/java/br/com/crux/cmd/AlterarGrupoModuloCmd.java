package br.com.crux.cmd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.GrupoModuloTOBuilder;
import br.com.crux.dao.repository.GrupoModuloRepository;
import br.com.crux.entity.GruposModulo;
import br.com.crux.exception.NotFoundException;
import br.com.crux.rule.CamposObrigatoriosGrupoModuloRule;
import br.com.crux.to.GrupoModuloTO;

@Component
public class AlterarGrupoModuloCmd {

	@Autowired private GrupoModuloRepository repository;
	@Autowired private CamposObrigatoriosGrupoModuloRule camposObrigatoriosRule;
	@Autowired private GetUsuarioLogadoCmd getUsuarioLogadoCmd;
	@Autowired private GrupoModuloTOBuilder toBuilder;

	public void alterar(GrupoModuloTO to) {
		camposObrigatoriosRule.verificar(to);

		GruposModulo entity = repository.findById(to.getId()).orElseThrow(() -> new NotFoundException("Grupo módulo informado não existe."));

		to.setUsuarioAlteracao(getUsuarioLogadoCmd.getUsuarioLogado().getIdUsuario());
		entity = toBuilder.buildTO(to);

		repository.save(entity);

	}
}
