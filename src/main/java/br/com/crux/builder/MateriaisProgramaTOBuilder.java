package br.com.crux.builder;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.cmd.GetMaterialCmd;
import br.com.crux.cmd.GetParceriasProgramaCmd;
import br.com.crux.cmd.GetUsuarioLogadoCmd;
import br.com.crux.entity.MateriaisPrograma;
import br.com.crux.entity.ParceriasPrograma;
import br.com.crux.entity.Programa;
import br.com.crux.to.MateriaisProgramaTO;

@Component
public class MateriaisProgramaTOBuilder {

	@Autowired GetUsuarioLogadoCmd getUsuarioLogadoCmd;
	@Autowired GetParceriasProgramaCmd getParceriasProgramaCmd;
	@Autowired GetMaterialCmd getMaterialCmd;
	@Autowired MaterialTOBuilder materialTOBuilder;
	@Autowired ParceriasProgramaTOBuilder parceriasProgramaTOBuilder;
	@Autowired ProgramaTOBuilder programaTOBuilder;
	
	
	public MateriaisPrograma buildEntity(Programa programa, ParceriasPrograma parceriasPrograma, MateriaisProgramaTO to) {
		MateriaisPrograma retorno = new MateriaisPrograma();
		BeanUtils.copyProperties(to, retorno);
		retorno.setPrograma(programa);
		retorno.setParceriasPrograma(parceriasPrograma);
		Optional.ofNullable(to.getMaterial()).ifPresent(m -> {
			retorno.setMaterial(getMaterialCmd.getById(m.getId()));
		});
		retorno.setUsuarioAlteracao(getUsuarioLogadoCmd.getUsuarioLogado().getIdUsuario());
		return retorno;
	}

	public MateriaisProgramaTO buildTO(MateriaisPrograma entity) {
		MateriaisProgramaTO to = new MateriaisProgramaTO();
		BeanUtils.copyProperties(entity, to);
		
		to.setPrograma(programaTOBuilder.buildTO(entity.getPrograma()));
		to.setMaterial(materialTOBuilder.buildTO(entity.getMaterial()));
		
		return to;
	}
	

	public MateriaisPrograma build(MateriaisProgramaTO entity) {
		MateriaisPrograma to = new MateriaisPrograma();
		BeanUtils.copyProperties(entity, to);
		
		to.setMaterial(materialTOBuilder.build(entity.getMaterial()));
		to.setParceriasPrograma(parceriasProgramaTOBuilder.buildEntity(entity.getParceriasPrograma()));
		return to;
	}

	public List<MateriaisProgramaTO> buildAll(List<MateriaisPrograma> lista) {
		return lista.stream().map(this::buildTO).collect(Collectors.toList());
	}

	public List<MateriaisPrograma> buildAllTO(List<MateriaisProgramaTO> lista) {
		return lista.stream().map(this::build).collect(Collectors.toList());
	}
}
