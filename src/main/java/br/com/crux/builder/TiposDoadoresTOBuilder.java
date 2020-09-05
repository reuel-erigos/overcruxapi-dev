package br.com.crux.builder;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.cmd.GetUsuarioLogadoCmd;
import br.com.crux.entity.TiposDoadores;
import br.com.crux.to.TiposDoadoresTO;

@Component
public class TiposDoadoresTOBuilder {

	@Autowired GetUsuarioLogadoCmd getUsuarioLogadoCmd;

	public TiposDoadoresTO buildTO(TiposDoadores entity) {
		TiposDoadoresTO to = new TiposDoadoresTO();
		BeanUtils.copyProperties(entity, to);
		return to;
	}

	public List<TiposDoadoresTO> buildAll(List<TiposDoadores> lista) {
		return lista.stream()
				.map(this::buildTO)
				.collect(Collectors.toList());
	}

	public TiposDoadores build(TiposDoadoresTO to) {
		TiposDoadores entity = new TiposDoadores();

		BeanUtils.copyProperties(to, entity);
		entity.setUsuarioAlteracao(getUsuarioLogadoCmd.getUsuarioLogado().getIdUsuario());

		return entity;
	}

}
