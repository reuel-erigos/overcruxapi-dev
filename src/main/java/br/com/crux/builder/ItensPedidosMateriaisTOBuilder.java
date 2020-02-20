package br.com.crux.builder;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import br.com.crux.entity.ItensPedidosMateriais;
import br.com.crux.to.ItensPedidosMateriaisTO;

@Component
public class ItensPedidosMateriaisTOBuilder {

	//TODO TERMINAR ESSE BUILDER QUEM PEGAR O CARTAO
	// FIZ APENAS ESSE METODO PQ TAVA PRECISANDO

	public ItensPedidosMateriaisTO buildTOCombo(ItensPedidosMateriais itensPedidosMateriais) {
		ItensPedidosMateriaisTO to = new ItensPedidosMateriaisTO();
		
		if(Objects.isNull(itensPedidosMateriais)) {
			return to;
		}

		BeanUtils.copyProperties(itensPedidosMateriais, to);

		return to;
	}

	public ItensPedidosMateriaisTO buildTO(ItensPedidosMateriais itensPedidosMateriais) {
		// TODO 
		return null;
	}

	public List<ItensPedidosMateriaisTO> buildAllTOCombo(List<ItensPedidosMateriais> lista) {
		return lista.stream()
				.map(this::buildTOCombo)
				.collect(Collectors.toList());

	}

}
