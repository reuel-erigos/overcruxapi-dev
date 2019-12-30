package br.com.crux.cmd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.MetasTOBuilder;
import br.com.crux.dao.repository.MetasRepository;
import br.com.crux.entity.Metas;
import br.com.crux.exception.NotFoundException;
import br.com.crux.rule.CamposObrigatoriosMetasRule;
import br.com.crux.to.MetasTO;

@Component
public class AlterarMetasCmd {

	@Autowired private MetasRepository metasRepository;
	@Autowired private GetUsuarioLogadoCmd getUsuarioLogadoCmd;
	@Autowired private MetasTOBuilder metasTOBuilder;
	@Autowired private CamposObrigatoriosMetasRule camposObrigatoriosRule;

	public void alterar(MetasTO to) {
		Metas entity = metasRepository.findById(to.getId()).orElseThrow(() -> new NotFoundException("Meta informada não existe."));

		camposObrigatoriosRule.verificar(to);

		to.setUsuarioAlteracao(getUsuarioLogadoCmd.getUsuarioLogado().getIdUsuario());

		entity = metasTOBuilder.build(to);

		metasRepository.save(entity);

	}
}
