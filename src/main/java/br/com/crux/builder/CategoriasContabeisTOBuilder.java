package br.com.crux.builder;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.cmd.GetCategoriasContabeisCmd;
import br.com.crux.cmd.GetUsuarioLogadoCmd;
import br.com.crux.entity.CategoriasContabeis;
import br.com.crux.to.CategoriasContabeisTO;

@Component
public class CategoriasContabeisTOBuilder {

	@Autowired private GetUsuarioLogadoCmd getUsuarioLogadoCmd;
	@Autowired private GetCategoriasContabeisCmd getCategoriasContabeisCmd;

	public CategoriasContabeis build(CategoriasContabeisTO to) {
		CategoriasContabeis entity = new CategoriasContabeis();

		BeanUtils.copyProperties(to, entity);
		entity.setUsuarioAlteracao(getUsuarioLogadoCmd.getUsuarioLogado().getIdUsuario());

		if (Objects.nonNull(to.getCategoriaSuperior()) && Objects.nonNull(to.getCategoriaSuperior().getId())) {
			entity.setCategoriaSuperior(getCategoriasContabeisCmd.getById(to.getCategoriaSuperior().getId()));
		}

		return entity;
	}

	public CategoriasContabeisTO buildTO(CategoriasContabeis entity) {
		CategoriasContabeisTO to = new CategoriasContabeisTO();

		if (Objects.isNull(entity)) {
			return to;
		}

		BeanUtils.copyProperties(entity, to);
		
		to.setSintetica(entity.getSintetica());
		
		to.setCategoriaSuperior(buildTOCategoriaSuperior(entity.getCategoriaSuperior()));

		return to;
	}

	public CategoriasContabeisTO buildTOCategoriaSuperior(CategoriasContabeis superior) {
		CategoriasContabeisTO to = new CategoriasContabeisTO();

		if (Objects.isNull(superior)) {
			return to;
		}

		BeanUtils.copyProperties(superior, to);
		return to;
	}

	public List<CategoriasContabeisTO> buildAll(List<CategoriasContabeis> dtos) {
		return dtos.stream()
				.map(dto -> buildTO(dto))
				.collect(Collectors.toList());
	}

	public CategoriasContabeisTO buildTOCombo(CategoriasContabeis categoria) {
		CategoriasContabeisTO to = new CategoriasContabeisTO();
		
		if(Objects.isNull(categoria)) {
			return to;
		}
		
		BeanUtils.copyProperties(categoria, to);
		
		return to;
	}
	
	public List<CategoriasContabeisTO> buildAllCombo(List<CategoriasContabeis> dtos) {
		return dtos.stream()
				.map(dto -> buildTOCombo(dto))
				.collect(Collectors.toList());
	}


}
