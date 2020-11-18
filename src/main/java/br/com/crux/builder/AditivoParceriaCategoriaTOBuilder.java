package br.com.crux.builder;

import java.util.Objects;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.cmd.GetUsuarioLogadoCmd;
import br.com.crux.entity.AditivoParceriaCategoria;
import br.com.crux.entity.ParceriasCategorias;
import br.com.crux.to.AditivoParceriaCategoriaTO;

@Component
public class AditivoParceriaCategoriaTOBuilder extends Builder<AditivoParceriaCategoria, AditivoParceriaCategoriaTO> {

	@Autowired
	private GetUsuarioLogadoCmd getUsuarioLogadoCmd;

	@Override
	public AditivoParceriaCategoriaTO buildTO(AditivoParceriaCategoria e) {
		AditivoParceriaCategoriaTO to = new AditivoParceriaCategoriaTO();

		if (Objects.isNull(e)) {
			return to;
		}

		BeanUtils.copyProperties(e, to);

		if (Objects.nonNull(e.getParceriasCategorias())) {
			to.setIdParceriasCategorias(e.getParceriasCategorias().getId());
		}

		return to;
	}

	public AditivoParceriaCategoria build(ParceriasCategorias p, AditivoParceriaCategoriaTO to) {
		AditivoParceriaCategoria e = new AditivoParceriaCategoria();

		BeanUtils.copyProperties(to, e);

		e.setParceriasCategorias(p);

		e.setUsuarioAlteracao(getUsuarioLogadoCmd.getUsuarioLogado().getIdUsuario());

		return e;
	}

}
