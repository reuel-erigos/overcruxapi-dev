package br.com.crux.builder;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.cmd.GetMaterialCmd;
import br.com.crux.cmd.GetUsuarioLogadoCmd;
import br.com.crux.entity.ItensPedidosMateriais;
import br.com.crux.entity.Material;
import br.com.crux.entity.PedidosMateriais;
import br.com.crux.to.ItensPedidosMateriaisTO;

@Component
public class ItensPedidosMateriaisTOBuilder {

	@Autowired private GetMaterialCmd getMaterialCmd;
	@Autowired private GetUsuarioLogadoCmd getUsuarioLogadoCmd;
	@Autowired private MaterialTOBuilder materialTOBuilder;
	@Autowired private PedidosMateriaisTOBuilder pedidosMateriaisTOBuilder;

	public ItensPedidosMateriais build(PedidosMateriais pedidosMateriais, ItensPedidosMateriaisTO to) {

		ItensPedidosMateriais e = new ItensPedidosMateriais();

		BeanUtils.copyProperties(to, e);

		if (Objects.nonNull(to.getMaterial()) && Objects.nonNull(to.getMaterial()
				.getId())) {
			Material material = getMaterialCmd.getById(to.getMaterial()
					.getId());
			e.setMaterial(material);
		}

		e.setPedidosMateriais(pedidosMateriais);
		
		e.setUsuarioAlteracao(getUsuarioLogadoCmd.getUsuarioLogado().getIdUsuario());

		return e;
	}

	public ItensPedidosMateriaisTO buildTO(ItensPedidosMateriais e) {
		ItensPedidosMateriaisTO to = new ItensPedidosMateriaisTO();

		BeanUtils.copyProperties(e, to);
		
		to.setMaterial(materialTOBuilder.buildTOCombo(e.getMaterial()));
		
		return to;

	}

	public ItensPedidosMateriaisTO buildTOCombo(ItensPedidosMateriais e) {
		ItensPedidosMateriaisTO to = new ItensPedidosMateriaisTO();

		if (Objects.isNull(e)) {
			return to;
		}

		BeanUtils.copyProperties(e, to);
		
		to.setPedidosMateriais(pedidosMateriaisTOBuilder.buildTOCombo(e.getPedidosMateriais()));
		

		return to;
	}

	public List<ItensPedidosMateriaisTO> buildAllTOCombo(List<ItensPedidosMateriais> lista) {
		return lista.stream()
				.map(this::buildTOCombo)
				.collect(Collectors.toList());

	}

	public List<ItensPedidosMateriaisTO> buildAll(List<ItensPedidosMateriais> lista) {
		return lista.stream()
				.map(this::buildTO)
				.collect(Collectors.toList());

	}

}
