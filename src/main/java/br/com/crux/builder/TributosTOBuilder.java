package br.com.crux.builder;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.cmd.GetCategoriasContabeisCmd;
import br.com.crux.cmd.GetUsuarioLogadoCmd;
import br.com.crux.entity.Tributos;
import br.com.crux.to.TributosTO;

@Component
public class TributosTOBuilder {

	@Autowired private GetUsuarioLogadoCmd getUsuarioLogadoCmd;
	@Autowired private CategoriasContabeisTOBuilder categoriasContabeisTOBuilder;
	@Autowired private GetCategoriasContabeisCmd getCategoriasContabeisCmd;

	public TributosTO buildTO(Tributos entity) {
		TributosTO to = new TributosTO();
		if(Objects.isNull(entity)) {
			return to;
		}
		
		BeanUtils.copyProperties(entity, to);
		to.setCategoria(categoriasContabeisTOBuilder.buildTO(entity.getCategoria()));
		return to;
	}

	public List<TributosTO> buildAll(List<Tributos> lista) {
		return lista.stream()
				.map(this::buildTO)
				.collect(Collectors.toList());
	}

	public Tributos build(TributosTO to) {
		Tributos entity = new Tributos();

		BeanUtils.copyProperties(to, entity);
		
		if(Objects.nonNull(to.getCategoria()) && Objects.nonNull(to.getCategoria().getId())) {
			entity.setCategoria(getCategoriasContabeisCmd.getById(to.getCategoria().getId()));
		}
		
		entity.setUsuarioAlteracao(getUsuarioLogadoCmd.getUsuarioLogado().getIdUsuario());
		return entity;
	}

}
