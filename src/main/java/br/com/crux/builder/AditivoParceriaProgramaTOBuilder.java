package br.com.crux.builder;

import java.util.Objects;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.cmd.GetUsuarioLogadoCmd;
import br.com.crux.entity.AditivoParceriaPrograma;
import br.com.crux.entity.ParceriasPrograma;
import br.com.crux.to.AditivoParceriaProgramaTO;

@Component
public class AditivoParceriaProgramaTOBuilder extends Builder<AditivoParceriaPrograma, AditivoParceriaProgramaTO> {

	@Autowired
	private GetUsuarioLogadoCmd getUsuarioLogadoCmd;

	@Override
	public AditivoParceriaProgramaTO buildTO(AditivoParceriaPrograma e) {
		AditivoParceriaProgramaTO to = new AditivoParceriaProgramaTO();

		if (Objects.isNull(e)) {
			return to;
		}

		BeanUtils.copyProperties(e, to);

		if (Objects.nonNull(e.getParceriasPrograma())) {
			to.setIdParceriasPrograma(e.getParceriasPrograma().getId());
		}

		return to;
	}

	public AditivoParceriaPrograma build(ParceriasPrograma p, AditivoParceriaProgramaTO to) {
		AditivoParceriaPrograma e = new AditivoParceriaPrograma();
		
		BeanUtils.copyProperties(to, e);
		
		e.setParceriasPrograma(p);
		
		e.setUsuarioAlteracao(getUsuarioLogadoCmd.getUsuarioLogado().getIdUsuario());
		
		return e;
	}

}
