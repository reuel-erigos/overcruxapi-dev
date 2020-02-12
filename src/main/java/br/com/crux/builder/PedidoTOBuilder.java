package br.com.crux.builder;

import org.springframework.stereotype.Component;

import br.com.crux.entity.Pedido;
import br.com.crux.to.PedidoTO;

@Component
public class PedidoTOBuilder {
	//TODO

	public PedidoTO buildTO(Pedido pedido) {
		// TODO Auto-generated method stub
		return new PedidoTO();
	}

}
