package br.com.crux.builder;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.cmd.GetUnidadeCmd;
import br.com.crux.cmd.GetUsuarioLogadoCmd;
import br.com.crux.entity.RateiosMovimentacoesUnidades;
import br.com.crux.to.RateiosMovimentacoesUnidadesTO;

@Component
public class RateiosMovimentacoesUnidadesTOBuilder {

	@Autowired private GetUsuarioLogadoCmd getUsuarioLogadoCmd;
	@Autowired private GetUnidadeCmd getUnidadeCmd;
	@Autowired private UnidadeTOBuilder unidadeTOBuilder;
	
	
	

	public RateiosMovimentacoesUnidadesTO buildTO(RateiosMovimentacoesUnidades m) {
		RateiosMovimentacoesUnidadesTO to = new RateiosMovimentacoesUnidadesTO();

		if (Objects.isNull(m)) {
			return to;
		}

		BeanUtils.copyProperties(m, to);
		
		to.setUnidade(unidadeTOBuilder.buildTOCombo(m.getUnidade()));

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

		if (Objects.nonNull(to.getUnidade()) && Objects.nonNull(to.getUnidade().getIdUnidade())) {
			p.setUnidade(getUnidadeCmd.getById(to.getUnidade().getIdUnidade()));
		}

		p.setUsuarioAlteracao(getUsuarioLogadoCmd.getUsuarioLogado().getIdUsuario());
		return p;
	}

}

