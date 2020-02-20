package br.com.crux.cmd;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.ItensPedidosMateriaisTOBuilder;
import br.com.crux.dao.repository.ItensPedidosMateriaisRepository;
import br.com.crux.entity.ItensPedidosMateriais;
import br.com.crux.entity.PedidosMateriais;
import br.com.crux.to.ItensPedidosMateriaisTO;

@Component
public class AlterarListaItensPedidosMateriaisCmd extends AbstractAlterarListaCmd<ItensPedidosMateriais, ItensPedidosMateriaisTO, PedidosMateriais> {

	@Autowired private ItensPedidosMateriaisTOBuilder toBuilder;
	@Autowired private GetItensPedidosMateriaisCmd getCmd;
	@Autowired private CadastrarItensPedidosMateriaisCmd cadastrarCmd;
	@Autowired private ItensPedidosMateriaisRepository repository;

	@Override
	protected ItensPedidosMateriaisTO getTO(ItensPedidosMateriais entity) {
		return toBuilder.buildTO(entity);
	}

	@Override
	protected List<ItensPedidosMateriaisTO> getTOListaBanco(List<ItensPedidosMateriais> lista) {
		return toBuilder.buildAll(lista);

	}

	@Override
	protected List<ItensPedidosMateriais> getListaBanco(PedidosMateriais pai) {
		return getCmd.getPorPedidosMateriais(p);
	}

	@Override
	protected Long getIdentificadorTO(ItensPedidosMateriaisTO to) {
		return to.getId();
	}

	@Override
	protected void cadastrar(ItensPedidosMateriaisTO to, PedidosMateriais p) {
		cadastrarCmd.cadastrar(to, p);

	}

	@Override
	protected void deletar(ItensPedidosMateriais registro) {
		repository.delete(registro);

	}

}
