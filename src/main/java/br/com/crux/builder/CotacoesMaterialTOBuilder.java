package br.com.crux.builder;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.cmd.GetEmpresaCmd;
import br.com.crux.cmd.GetMaterialCmd;
import br.com.crux.cmd.GetPedidosMateriaisCmd;
import br.com.crux.cmd.GetUsuarioLogadoCmd;
import br.com.crux.entity.CotacoesMaterial;
import br.com.crux.entity.Empresa;
import br.com.crux.entity.Material;
import br.com.crux.entity.PedidosMateriais;
import br.com.crux.to.CotacoesMaterialTO;

@Component
public class CotacoesMaterialTOBuilder {

	@Autowired private EmpresaTOBuilder empresaBuilder;
	@Autowired private MaterialTOBuilder materialBuilder;
	@Autowired private PedidoTOBuilder pedidoBuilder;

	@Autowired private GetEmpresaCmd getEmpresaCmd;
	@Autowired private GetMaterialCmd getMaterialCmd;
	@Autowired private GetPedidosMateriaisCmd getPedidosMateriaisCmd;

	@Autowired private GetUsuarioLogadoCmd getUsuarioLogadoCmd;

	public CotacoesMaterial build(CotacoesMaterialTO to) {
		CotacoesMaterial entity = new CotacoesMaterial();

		BeanUtils.copyProperties(to, entity);

		if (Objects.nonNull(to.getEmpresa()) && Objects.nonNull(to.getEmpresa()
				.getId())) {
			Empresa empresa = getEmpresaCmd.getById(to.getEmpresa()
					.getId());
			entity.setEmpresa(empresa);
		}

		if (Objects.nonNull(to.getMaterial()) && Objects.nonNull(to.getMaterial()
				.getId())) {
			Material material = getMaterialCmd.getById(to.getMaterial()
					.getId());
			entity.setMaterial(material);
		}

		if (Objects.nonNull(to.getPedidosMaterial()) && Objects.nonNull(to.getPedidosMaterial()
				.getId())) {
			PedidosMateriais pedido = getPedidosMateriaisCmd.getById(to.getPedidosMaterial()
					.getId());
			entity.setPedidosMateriais(pedido);
		}

		entity.setUsuarioAlteracao(getUsuarioLogadoCmd.getUsuarioLogado()
				.getIdUsuario());

		return entity;
	}

	public CotacoesMaterialTO buildTO(CotacoesMaterial entity) {
		CotacoesMaterialTO to = new CotacoesMaterialTO();

		if (Objects.isNull(entity)) {
			return to;
		}

		BeanUtils.copyProperties(entity, to);

		to.setEmpresa(empresaBuilder.buildTO(entity.getEmpresa()));
		to.setMaterial(materialBuilder.buildTO(entity.getMaterial()));
		to.setPedidosMaterial(pedidoBuilder.buildTO(entity.getPedidosMaterial()));

		return to;
	}

	public List<CotacoesMaterialTO> buildAll(List<CotacoesMaterial> dtos) {
		return dtos.stream()
				.map(dto -> buildTO(dto))
				.collect(Collectors.toList());
	}

}
