package br.com.crux.builder;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.cmd.GetUnidadeLogadaCmd;
import br.com.crux.cmd.GetUsuarioLogadoCmd;
import br.com.crux.entity.TiposDoadores;
import br.com.crux.to.TiposDoadoresTO;

@Component
public class TiposDoadoresTOBuilder {

	@Autowired private GetUsuarioLogadoCmd getUsuarioLogadoCmd;
	@Autowired private GetUnidadeLogadaCmd getUnidadeLogadaCmd;

	public TiposDoadoresTO buildTO(TiposDoadores entity) {
		TiposDoadoresTO to = new TiposDoadoresTO();
		
		if(Objects.isNull(entity)) {
			return to;
		}
		
		BeanUtils.copyProperties(entity, to);
		
		if(entity.getIdInstituicao() != null) {
			to.setIdInstituicao(entity.getIdInstituicao());
		}
		
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
		
		Long idInstituicao = getUnidadeLogadaCmd.getUnidadeTO().getInstituicao().getId();
		entity.setIdInstituicao(idInstituicao);
		entity.setUsuarioAlteracao(getUsuarioLogadoCmd.getUsuarioLogado().getIdUsuario());

		return entity;
	}

}
