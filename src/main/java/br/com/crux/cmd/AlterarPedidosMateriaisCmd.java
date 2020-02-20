package br.com.crux.cmd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.PedidosMateriaisTOBuilder;
import br.com.crux.dao.repository.PedidosMateriaisRepository;
import br.com.crux.entity.PedidosMateriais;
import br.com.crux.exception.NotFoundException;
import br.com.crux.rule.CamposObrigatoriosPedidosMateriaisRule;
import br.com.crux.to.PedidosMateriaisTO;

@Component
public class AlterarPedidosMateriaisCmd {

	@Autowired private PedidosMateriaisRepository repository;
	@Autowired private PedidosMateriaisTOBuilder toBuilder;
	@Autowired private CamposObrigatoriosPedidosMateriaisRule rule;
	@Autowired private AlterarListaItensPedidosMateriaisCmd alterarListaItensPedidosMateriaisCmd;
	

	public void alterar(PedidosMateriaisTO to) {
		PedidosMateriais entity = repository.findById(to.getId())
				.orElseThrow(() -> new NotFoundException("Entidade informada não existe."));

		rule.verificar(to);

		entity = toBuilder.build(to);

		PedidosMateriais pedidosMateriais = repository.save(entity);
		
		alterarListaItensPedidosMateriaisCmd.alterarAll(to.getItensPedidosMateriais(), pedidosMateriais);
		
		

	}

}
