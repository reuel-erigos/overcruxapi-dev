package br.com.crux.cmd;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.PedidosMateriaisTOBuilder;
import br.com.crux.dao.repository.PedidosMateriaisRepository;
import br.com.crux.entity.PedidosMateriais;
import br.com.crux.exception.NotFoundException;
import br.com.crux.to.PedidosMateriaisTO;

@Component
public class GetPedidosMateriaisCmd {

	@Autowired private PedidosMateriaisRepository repository;
	@Autowired private PedidosMateriaisTOBuilder toBuilder;

	public List<PedidosMateriaisTO> getAllCombo() {

		List<PedidosMateriais> entitys = repository.findAll();
		if (!entitys.isEmpty()) {
			return toBuilder.buildAllCombo(entitys);
		}
		return new ArrayList<PedidosMateriaisTO>();

	}

	public List<PedidosMateriaisTO> getAll() {
		List<PedidosMateriais> entitys = repository.findAll();
		if (!entitys.isEmpty()) {
			return toBuilder.buildAll(entitys);
		}
		return new ArrayList<PedidosMateriaisTO>();
	}

	public PedidosMateriaisTO getTOById(Long id) {
		PedidosMateriais entity = repository.findById(id)
				.orElseThrow(() -> new NotFoundException("Pedido Material não encontrado."));
		return toBuilder.buildTO(entity);
	}

}
