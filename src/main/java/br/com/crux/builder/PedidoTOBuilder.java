package br.com.crux.builder;

import org.springframework.stereotype.Component;

import br.com.crux.entity.PedidosMateriais;
import br.com.crux.to.PedidosMateriaisTO;

@Component
public class PedidoTOBuilder {
	//TODO

	public PedidosMateriaisTO buildTO(PedidosMateriais pedido) {
		// TODO Auto-generated method stub
		return new PedidosMateriaisTO();
	}

}
