package br.com.crux.builder;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.cmd.GetUsuarioLogadoCmd;
import br.com.crux.entity.EstruturaUnidade;
import br.com.crux.entity.Unidade;
import br.com.crux.to.EstruturaUnidadeTO;

@Component
public class EstruturaUnidadeTOBuilder {

	@Autowired
	private GetUsuarioLogadoCmd getUsuarioLogadoCmd;

	public EstruturaUnidadeTO buildTO(EstruturaUnidade entity) {
		EstruturaUnidadeTO to = new EstruturaUnidadeTO();

		if (Objects.isNull(entity)) {
			return to;
		}

		BeanUtils.copyProperties(entity, to);

		return to;
	}

	public List<EstruturaUnidadeTO> buildAll(List<EstruturaUnidade> lista) {
		return lista.stream().map(this::buildTO).collect(Collectors.toList());
	}

	public EstruturaUnidade build(EstruturaUnidadeTO to, Unidade unidade) {
		EstruturaUnidade entity = new EstruturaUnidade();

		BeanUtils.copyProperties(to, entity);

		entity.setUnidade(unidade);
		entity.setUsuarioAlteracao(
				getUsuarioLogadoCmd.getUsuarioLogado().getIdUsuario());

		return entity;
	}

}
