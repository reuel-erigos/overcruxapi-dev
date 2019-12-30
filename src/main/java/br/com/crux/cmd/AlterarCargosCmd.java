package br.com.crux.cmd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.CargosTOBuilder;
import br.com.crux.dao.repository.CargoRepository;
import br.com.crux.entity.Cargo;
import br.com.crux.exception.NotFoundException;
import br.com.crux.rule.CamposObrigatoriosCargosRule;
import br.com.crux.to.CargoTO;

@Component
public class AlterarCargosCmd {

	@Autowired private CargoRepository repository;
	@Autowired private GetUsuarioLogadoCmd getUsuarioLogadoCmd;
	@Autowired private CamposObrigatoriosCargosRule camposObrigatoriosRule;
	@Autowired private CargosTOBuilder cargosTOBuilder;

	public void alterar(CargoTO to) {
		camposObrigatoriosRule.verificar(to);

		Cargo entity = repository.findById(to.getId()).orElseThrow(() -> new NotFoundException("Cargo informado não existe."));

		to.setUsuarioAlteracao(getUsuarioLogadoCmd.getUsuarioLogado().getIdUsuario());

		entity = cargosTOBuilder.build(to);

		repository.save(entity);

	}
}
