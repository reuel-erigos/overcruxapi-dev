package br.com.crux.builder;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import br.com.crux.entity.PedidosMateriais;
import br.com.crux.to.PedidosMateriaisTO;

@Component
public class PedidosMateriaisTOBuilder {

	//TODO completar quem for fazer o cartao

	public PedidosMateriaisTO buildTOCombo(PedidosMateriais pedidosMateriais) {
		PedidosMateriaisTO to = new PedidosMateriaisTO();
		
		if(Objects.isNull(pedidosMateriais)) {
			return to;
		}
		
		to.setId(pedidosMateriais.getId());
		to.setDescricaoPedido(pedidosMateriais.getDescricaoPedido());

		return to;
	}

	public List<PedidosMateriaisTO> buildAllCombo(List<PedidosMateriais> list) {
		return list.stream().map(this::buildTOCombo).collect(Collectors.toList());
	}

}
