package br.com.crux.cmd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.CargosTOBuilder;
import br.com.crux.dao.repository.CargoRepository;
import br.com.crux.entity.Cargo;
import br.com.crux.rule.CamposObrigatoriosCargosRule;
import br.com.crux.to.CargoTO;

@Component
public class CadastrarCargosCmd {

	@Autowired private CargoRepository repository;

	@Autowired private GetUsuarioLogadoCmd getUsuarioLogadoCmd;
	@Autowired private CamposObrigatoriosCargosRule camposObrigatoriosRule;
	@Autowired private CargosTOBuilder cargosTOBuilder;

	public void cadastrar(CargoTO to) {

		camposObrigatoriosRule.verificar(to);

		to.setUsuarioAlteracao(getUsuarioLogadoCmd.getUsuarioLogado().getIdUsuario());

		Cargo entity = cargosTOBuilder.build(to);

		repository.save(entity);

	}
}
