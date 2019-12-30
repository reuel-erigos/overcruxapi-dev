package br.com.crux.cmd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.AcaoTOBuilder;
import br.com.crux.dao.repository.AcaoRepository;
import br.com.crux.entity.Acoes;
import br.com.crux.rule.CamposObrigatoriosAcaoRule;
import br.com.crux.to.AcaoTO;

@Component
public class CadastrarAcaoCmd {

	@Autowired private GetUsuarioLogadoCmd getUsuarioLogadoCmd;
	@Autowired private AcaoRepository repository;
	@Autowired private AcaoTOBuilder acaoTOBuilder;

	@Autowired private CamposObrigatoriosAcaoRule camposObrigatoriosRule;

	public void cadastrar(AcaoTO to) {

		camposObrigatoriosRule.verificar(to);

		to.setUsuarioAlteracao(getUsuarioLogadoCmd.getUsuarioLogado().getIdUsuario());

		Acoes entity = acaoTOBuilder.build(to);

		repository.save(entity);

	}
}
