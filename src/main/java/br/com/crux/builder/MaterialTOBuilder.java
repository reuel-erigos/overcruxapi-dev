package br.com.crux.builder;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.cmd.GetUsuarioLogadoCmd;
import br.com.crux.entity.Material;
import br.com.crux.to.MaterialTO;

@Component
public class MaterialTOBuilder {

	@Autowired
	private GetUsuarioLogadoCmd getUsuarioLogadoCmd;

	public Material build(MaterialTO param) {
		Material retorno = new Material();

		BeanUtils.copyProperties(param, retorno);

		retorno.setUsuarioAlteracao(getUsuarioLogadoCmd.getUsuarioLogado().getIdUsuario());

		return retorno;
	}

	public MaterialTO buildTO(Material dto) {
		MaterialTO retorno = new MaterialTO();

		BeanUtils.copyProperties(dto, retorno);

		return retorno;
	}

	public List<MaterialTO> buildAll(List<Material> list) {
		return list.stream().map(dto -> buildTO(dto)).collect(Collectors.toList());
	}

}
