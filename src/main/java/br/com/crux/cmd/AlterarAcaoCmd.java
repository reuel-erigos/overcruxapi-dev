package br.com.crux.cmd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.AcaoTOBuilder;
import br.com.crux.dao.repository.AcaoRepository;
import br.com.crux.entity.Acoes;
import br.com.crux.exception.NotFoundException;
import br.com.crux.rule.CamposObrigatoriosAcaoRule;
import br.com.crux.to.AcaoTO;

@Component
public class AlterarAcaoCmd {

	@Autowired private GetUsuarioLogadoCmd getUsuarioLogadoCmd;

	@Autowired private AcaoRepository repository;
	@Autowired private CamposObrigatoriosAcaoRule camposObrigatoriosRule;
	@Autowired private AcaoTOBuilder acaoTOBuilder;

	public void alterar(AcaoTO to) {
		camposObrigatoriosRule.verificar(to);

		Acoes entity = repository.findById(to.getId()).orElseThrow(() -> new NotFoundException("Ação informada não existe."));

		to.setUsuarioAlteracao(getUsuarioLogadoCmd.getUsuarioLogado().getIdUsuario());

		entity = acaoTOBuilder.build(to);

		repository.save(entity);

	}
}
