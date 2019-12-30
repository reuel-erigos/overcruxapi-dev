package br.com.crux.cmd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.PlanosAcaoTOBuilder;
import br.com.crux.dao.repository.PlanosAcaoRepository;
import br.com.crux.entity.PlanosAcao;
import br.com.crux.rule.CamposObrigatoriosPlanosAcaoRule;
import br.com.crux.to.PlanosAcaoTO;

@Component
public class CadastrarPlanosAcaoCmd {

	@Autowired private PlanosAcaoRepository repository;

	@Autowired private PlanosAcaoTOBuilder planosAcaoTOBuilder;

	@Autowired private CamposObrigatoriosPlanosAcaoRule camposObrigatoriosRule;
	@Autowired private GetUsuarioLogadoCmd getUsuarioLogadoCmd;

	public void cadastrar(PlanosAcaoTO to) {

		camposObrigatoriosRule.verificar(to);

		to.setUsuarioAlteracao(getUsuarioLogadoCmd.getUsuarioLogado().getIdUsuario());

		PlanosAcao entity = planosAcaoTOBuilder.build(to);

		repository.save(entity);

	}
}
