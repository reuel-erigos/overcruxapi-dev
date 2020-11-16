package br.com.crux.builder;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.cmd.GetInstituicaoCmd;
import br.com.crux.cmd.GetUsuarioLogadoCmd;
import br.com.crux.entity.TiposAtividades;
import br.com.crux.to.TiposAtividadesTO;

@Component
public class TiposAtividadesTOBuilder {

	@Autowired
	private GetUsuarioLogadoCmd getUsuarioLogadoCmd;
	@Autowired
	private GetInstituicaoCmd getInstituicaoCmd;
	@Autowired
	private InstituicaoTOBuilder instituicaoTOBuilder;

	public TiposAtividadesTO buildTO(TiposAtividades entity) {
		TiposAtividadesTO to = new TiposAtividadesTO();
		
		if(Objects.isNull(entity)) {
			return to;
		}
		
		BeanUtils.copyProperties(entity, to);
		
		to.setInstituicao(instituicaoTOBuilder.buildTO(entity.getInstituicao()));
		
		return to;
	}

	public List<TiposAtividadesTO> buildAll(List<TiposAtividades> lista) {
		return lista.stream().map(this::buildTO).collect(Collectors.toList());
	}

	public TiposAtividades build(TiposAtividadesTO to) {
		TiposAtividades entity = new TiposAtividades();

		BeanUtils.copyProperties(to, entity);
		
		if(Objects.nonNull(to.getInstituicao()) && Objects.nonNull(to.getInstituicao().getId())) {
			entity.setInstituicao(getInstituicaoCmd.getById(to.getInstituicao().getId()));
		}
		
		entity.setUsuarioAlteracao(getUsuarioLogadoCmd.getUsuarioLogado().getIdUsuario());

		return entity;
	}

}
