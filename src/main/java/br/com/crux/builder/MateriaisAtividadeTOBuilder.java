package br.com.crux.builder;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.cmd.GetMaterialCmd;
import br.com.crux.entity.MateriaisAtividade;
import br.com.crux.entity.Material;
import br.com.crux.to.MateriaisAtividadeTO;

@Component
public class MateriaisAtividadeTOBuilder {

	@Autowired
	private MaterialTOBuilder materialBuilder;
	@Autowired
	private GetMaterialCmd getMaterialCmd;

	public MateriaisAtividade build(MateriaisAtividadeTO p) {
		MateriaisAtividade retorno = new MateriaisAtividade();

		BeanUtils.copyProperties(p, retorno);

		Optional.ofNullable(p.getMaterial()).ifPresent(material -> {
			if (Objects.nonNull(material.getId())) {
				Material mat = getMaterialCmd.getById(material.getId());
				retorno.setMaterial(mat);
			}
		});

		retorno.setUsuarioAlteracao(p.getUsuarioAlteracao());

		return retorno;
	}

	public MateriaisAtividadeTO buildTO(MateriaisAtividade p) {
		MateriaisAtividadeTO retorno = new MateriaisAtividadeTO();

		BeanUtils.copyProperties(p, retorno);

		retorno.setMaterial(materialBuilder.buildTO(p.getMaterial()));
		retorno.setUsuarioAlteracao(p.getUsuarioAlteracao());

		return retorno;
	}

	public List<MateriaisAtividadeTO> buildAll(List<MateriaisAtividade> dtos) {
		return dtos.stream().map(dto -> buildTO(dto)).collect(Collectors.toList());
	}

}
