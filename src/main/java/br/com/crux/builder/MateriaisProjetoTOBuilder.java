package br.com.crux.builder;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.cmd.GetMaterialCmd;
import br.com.crux.cmd.GetParceriasProjetoCmd;
import br.com.crux.cmd.GetUsuarioLogadoCmd;
import br.com.crux.entity.MateriaisProjeto;
import br.com.crux.entity.ParceriasProjeto;
import br.com.crux.entity.Projeto;
import br.com.crux.to.MateriaisProjetoTO;

@Component
public class MateriaisProjetoTOBuilder {

	@Autowired GetUsuarioLogadoCmd getUsuarioLogadoCmd;
	@Autowired GetParceriasProjetoCmd getParceriasProjetoCmd;
	@Autowired GetMaterialCmd getMaterialCmd;
	@Autowired MaterialTOBuilder materialTOBuilder;
	@Autowired ParceriasProjetoTOBuilder parceriasProjetoTOBuilder;

	public MateriaisProjeto build(Projeto projeto, ParceriasProjeto parceriasProjeto, MateriaisProjetoTO to) {

		MateriaisProjeto retorno = new MateriaisProjeto();

		BeanUtils.copyProperties(to, retorno);

		retorno.setProjeto(projeto);
		
		retorno.setParceriasProjeto(parceriasProjeto);

		Optional.ofNullable(to.getMaterial()).ifPresent(m -> {
			retorno.setMaterial(getMaterialCmd.getById(m.getId()));
		});

		retorno.setUsuarioAlteracao(getUsuarioLogadoCmd.getUsuarioLogado().getIdUsuario());

		return retorno;
	}

	public MateriaisProjetoTO buildTO(MateriaisProjeto entity) {

		MateriaisProjetoTO to = new MateriaisProjetoTO();

		BeanUtils.copyProperties(entity, to);

		materialTOBuilder.buildTO(entity.getMaterial());

		to.setMaterial(materialTOBuilder.buildTO(entity.getMaterial()));
		

		return to;
	}

	public List<MateriaisProjetoTO> buildAll(List<MateriaisProjeto> lista) {

		return lista.stream().map(this::buildTO).collect(Collectors.toList());
	}

}
