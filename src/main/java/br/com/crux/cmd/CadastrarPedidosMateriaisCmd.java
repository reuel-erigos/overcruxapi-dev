package br.com.crux.cmd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.PedidosMateriaisTOBuilder;
import br.com.crux.dao.repository.PedidosMateriaisRepository;
import br.com.crux.entity.PedidosMateriais;
import br.com.crux.rule.CamposObrigatoriosPedidosMateriaisRule;
import br.com.crux.to.PedidosMateriaisTO;

@Component
public class CadastrarPedidosMateriaisCmd {

	@Autowired private PedidosMateriaisRepository repository;
	@Autowired private PedidosMateriaisTOBuilder toBuilder;
	@Autowired private CamposObrigatoriosPedidosMateriaisRule rule;
	@Autowired private CadastrarItensPedidosMateriaisCmd cadastrarItensPedidosMateriaisCmd;

	public void cadastrar(PedidosMateriaisTO to) {
		
		rule.verificar(to);
		
		PedidosMateriais entity = toBuilder.build(to);
		
		PedidosMateriais pedidosMateriais = repository.save(entity);
		
		cadastrarItensPedidosMateriaisCmd.cadastrarLista(pedidosMateriais, to.getItensPedidosMateriais());

	}
}
