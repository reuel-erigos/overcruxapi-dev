package br.com.crux.cmd;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.ItensPedidosMateriaisTOBuilder;
import br.com.crux.dao.repository.ItensPedidosMateriaisRepository;
import br.com.crux.entity.ItensPedidosMateriais;
import br.com.crux.entity.PedidosMateriais;
import br.com.crux.to.ItensPedidosMateriaisTO;

@Component
public class CadastrarItensPedidosMateriaisCmd {

	@Autowired ItensPedidosMateriaisRepository repository;
	@Autowired ItensPedidosMateriaisTOBuilder tOBuilder;

	public ItensPedidosMateriais cadastrar(ItensPedidosMateriaisTO itensPedidosMateriaisTO, PedidosMateriais pedidosMateriais) {
		ItensPedidosMateriais entity = tOBuilder.build(pedidosMateriais, itensPedidosMateriaisTO);
		return repository.save(entity);
	}

	public List<ItensPedidosMateriais> cadastrarLista(PedidosMateriais pedidosMateriais, List<ItensPedidosMateriaisTO> list) {
		return list.stream()
				.map(item -> cadastrar(item, pedidosMateriais))
				.collect(Collectors.toList());

	}

}
