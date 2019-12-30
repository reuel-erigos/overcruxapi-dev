package br.com.crux.cmd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.PlanosAcaoTOBuilder;
import br.com.crux.dao.repository.PlanosAcaoRepository;
import br.com.crux.entity.PlanosAcao;
import br.com.crux.exception.NotFoundException;
import br.com.crux.rule.CamposObrigatoriosPlanosAcaoRule;
import br.com.crux.to.PlanosAcaoTO;

@Component
public class AlterarPlanosAcaoCmd {

	@Autowired private PlanosAcaoRepository repository;
	@Autowired private PlanosAcaoTOBuilder planosAcaoTOBuilder;

	@Autowired private CamposObrigatoriosPlanosAcaoRule camposObrigatoriosRule;

	@Autowired private GetUsuarioLogadoCmd getUsuarioLogadoCmd;

	public void alterar(PlanosAcaoTO to) {
		PlanosAcao entity = repository.findById(to.getId()).orElseThrow(() -> new NotFoundException("Planos de Ação informado não existe."));

		camposObrigatoriosRule.verificar(to);

		to.setUsuarioAlteracao(getUsuarioLogadoCmd.getUsuarioLogado().getIdUsuario());

		entity = planosAcaoTOBuilder.build(to);

		repository.save(entity);

	}
}
