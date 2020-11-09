package br.com.crux.builder;

import java.util.Objects;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.cmd.GetUsuarioLogadoCmd;
import br.com.crux.entity.AditivoParceriaProjeto;
import br.com.crux.entity.ParceriasProjeto;
import br.com.crux.to.AditivoParceriaProjetoTO;

@Component
public class AditivoParceriaProjetoTOBuilder extends Builder<AditivoParceriaProjeto, AditivoParceriaProjetoTO> {

	@Autowired
	private GetUsuarioLogadoCmd getUsuarioLogadoCmd;

	@Override
	public AditivoParceriaProjetoTO buildTO(AditivoParceriaProjeto e) {
		AditivoParceriaProjetoTO to = new AditivoParceriaProjetoTO();

		if (Objects.isNull(e)) {
			return to;
		}

		BeanUtils.copyProperties(e, to);

		if (Objects.nonNull(e.getParceriasProjeto())) {
			to.setIdParceriasProjeto(e.getParceriasProjeto().getId());
		}

		return to;
	}

	public AditivoParceriaProjeto build(ParceriasProjeto p, AditivoParceriaProjetoTO to) {
		AditivoParceriaProjeto e = new AditivoParceriaProjeto();

		BeanUtils.copyProperties(to, e);

		e.setParceriasProjeto(p);

		e.setUsuarioAlteracao(getUsuarioLogadoCmd.getUsuarioLogado().getIdUsuario());

		return e;
	}

}
