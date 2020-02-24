package br.com.crux.builder;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.cmd.GetMaterialCmd;
import br.com.crux.entity.MateriaisAcoes;
import br.com.crux.entity.Material;
import br.com.crux.to.MateriaisAcoesTO;

@Component
public class MateriaisAcoesTOBuilder {

	@Autowired private MaterialTOBuilder materialBuilder;
	@Autowired private GetMaterialCmd getMaterialCmd;

	
	public MateriaisAcoes build(MateriaisAcoesTO p) {
		MateriaisAcoes retorno = new MateriaisAcoes();

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

	public MateriaisAcoesTO buildTO(MateriaisAcoes p) {
		MateriaisAcoesTO retorno = new MateriaisAcoesTO();

		BeanUtils.copyProperties(p, retorno);

		retorno.setMaterial(materialBuilder.buildTO(p.getMaterial()));
		retorno.setUsuarioAlteracao(p.getUsuarioAlteracao());

		return retorno;
	}

	public List<MateriaisAcoesTO> buildAll(List<MateriaisAcoes> dtos) {
		return dtos.stream().map(dto -> buildTO(dto)).collect(Collectors.toList());
	}

	public List<MateriaisAcoes> buildTOAll(List<MateriaisAcoesTO> dtos) {
		return dtos.stream().map(dto -> build(dto)).collect(Collectors.toList());
	}
	
	
}
