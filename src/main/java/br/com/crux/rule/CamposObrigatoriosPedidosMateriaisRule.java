package br.com.crux.rule;

import java.util.Objects;

import org.springframework.stereotype.Component;

import br.com.crux.exception.CamposObrigatoriosException;
import br.com.crux.to.PedidosMateriaisTO;

@Component
public class CamposObrigatoriosPedidosMateriaisRule {

	public void verificar(PedidosMateriaisTO to) {

		if (Objects.isNull(to.getFuncionarioPedido()) && Objects.isNull(to.getFuncionarioPedido()
				.getId())) {
			throw new CamposObrigatoriosException("O Funcionário responsável pelo pedido deve ser informado.");
		}

	}
}
