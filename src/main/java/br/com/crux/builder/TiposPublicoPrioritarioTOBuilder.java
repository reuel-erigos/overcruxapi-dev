package br.com.crux.builder;

import java.util.Objects;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.cmd.GetUsuarioLogadoCmd;
import br.com.crux.entity.TiposPublicoPrioritario;
import br.com.crux.to.TiposPublicoPrioritarioTO;

@Component
public class TiposPublicoPrioritarioTOBuilder extends Builder<TiposPublicoPrioritario, TiposPublicoPrioritarioTO>{

	@Autowired
	private GetUsuarioLogadoCmd getUsuarioLogadoCmd;

	public TiposPublicoPrioritarioTO buildTO(TiposPublicoPrioritario entity) {
		TiposPublicoPrioritarioTO to = new TiposPublicoPrioritarioTO();
		
		if(Objects.isNull(entity)) {
			return to;
		}
		
		BeanUtils.copyProperties(entity, to);
		
		
		return to;
	}


	public TiposPublicoPrioritario build(TiposPublicoPrioritarioTO to) {
		TiposPublicoPrioritario entity = new TiposPublicoPrioritario();

		BeanUtils.copyProperties(to, entity);
		
		entity.setUsuarioAlteracao(getUsuarioLogadoCmd.getUsuarioLogado().getIdUsuario());

		return entity;
	}

}
