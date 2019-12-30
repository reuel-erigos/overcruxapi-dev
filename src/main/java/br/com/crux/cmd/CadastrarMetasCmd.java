package br.com.crux.cmd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.MetasTOBuilder;
import br.com.crux.dao.repository.MetasRepository;
import br.com.crux.entity.Metas;
import br.com.crux.rule.CamposObrigatoriosMetasRule;
import br.com.crux.to.MetasTO;

@Component
public class CadastrarMetasCmd {

	@Autowired private MetasRepository metasRepository;
	@Autowired private CamposObrigatoriosMetasRule camposObrigatoriosRule;
	@Autowired private GetUsuarioLogadoCmd getUsuarioLogadoCmd;
	@Autowired private MetasTOBuilder metasTOBuilder;

	public void cadastrar(MetasTO to) {

		camposObrigatoriosRule.verificar(to);

		to.setUsuarioAlteracao(getUsuarioLogadoCmd.getUsuarioLogado().getIdUsuario());

		Metas entity = metasTOBuilder.build(to);

		metasRepository.save(entity);

	}
}
