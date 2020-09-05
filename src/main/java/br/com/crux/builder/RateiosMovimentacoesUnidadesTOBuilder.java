package br.com.crux.builder;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.cmd.GetUsuarioLogadoCmd;
import br.com.crux.entity.RateiosMovimentacoesUnidades;
import br.com.crux.to.RateiosMovimentacoesUnidadesTO;

@Component
public class RateiosMovimentacoesUnidadesTOBuilder {

	@Autowired private GetUsuarioLogadoCmd getUsuarioLogadoCmd;

	
	public RateiosMovimentacoesUnidadesTO buildTO(RateiosMovimentacoesUnidades m) {
		RateiosMovimentacoesUnidadesTO to = new RateiosMovimentacoesUnidadesTO();

		if (Objects.isNull(m)) {
			return to;
		}

		BeanUtils.copyProperties(m, to);
		
		return to;
	}

	public List<RateiosMovimentacoesUnidadesTO> buildAllTO(List<RateiosMovimentacoesUnidades> list) {
		return list.stream()
				.map(this::buildTO)
				.collect(Collectors.toList());
	}
	
	public List<RateiosMovimentacoesUnidades> buildAll(List<RateiosMovimentacoesUnidadesTO> list) {
		return list.stream()
				.map(this::build)
				.collect(Collectors.toList());
	}

	public RateiosMovimentacoesUnidades build(RateiosMovimentacoesUnidadesTO to) {
		RateiosMovimentacoesUnidades p = new RateiosMovimentacoesUnidades();

		BeanUtils.copyProperties(to, p);

		p.setUsuarioAlteracao(getUsuarioLogadoCmd.getUsuarioLogado().getIdUsuario());
		return p;
	}

}

