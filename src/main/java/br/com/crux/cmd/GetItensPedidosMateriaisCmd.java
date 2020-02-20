package br.com.crux.cmd;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.ItensPedidosMateriaisTOBuilder;
import br.com.crux.dao.repository.ItensPedidosMateriaisRepository;
import br.com.crux.entity.ItensPedidosMateriais;
import br.com.crux.entity.PedidosMateriais;
import br.com.crux.exception.NotFoundException;
import br.com.crux.to.ItensPedidosMateriaisTO;

@Component
public class GetItensPedidosMateriaisCmd {

	@Autowired private ItensPedidosMateriaisTOBuilder toBuilder;
	@Autowired private ItensPedidosMateriaisRepository repository;

	public ItensPedidosMateriaisTO getTOById(Long id) {
		Optional<ItensPedidosMateriais> entityOptional = repository.findById(id);
		if (!entityOptional.isPresent()) {
			throw new NotFoundException("Item movimentação não encontrado.");
		}
		return toBuilder.buildTO(entityOptional.get());
	}

	public ItensPedidosMateriais getById(Long id) {
		return repository.findById(id)
				.orElse(null);
	}

	public List<ItensPedidosMateriaisTO> getAllCombo() {

		List<ItensPedidosMateriais> lista = repository.findAll();

		if (!lista.isEmpty()) {
			return toBuilder.buildAllTOCombo(lista);
		}

		return Collections.emptyList();
	}

	public List<ItensPedidosMateriais> getPorPedidosMateriais(PedidosMateriais p) {

		return repository.findByPedidosMateriais(p)
				.orElse(new ArrayList<ItensPedidosMateriais>());

	}

	public List<ItensPedidosMateriaisTO> getItensPedidosMateriaisTOByPedidosMateriais(PedidosMateriais pedidosMateriais) {
		Optional<List<ItensPedidosMateriais>> lista = repository.findByPedidosMateriais(pedidosMateriais);

		if (lista.isPresent()) {
			return toBuilder.buildAll(lista.get());
		}

		return Collections.emptyList();

	}

}
