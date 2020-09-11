package br.com.crux.builder;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.cmd.GetTributoCmd;
import br.com.crux.cmd.GetUsuarioLogadoCmd;
import br.com.crux.entity.TributosMovimentacoes;
import br.com.crux.to.TributosMovimentacoesTO;

@Component
public class TributoMovimentacaoTOBuilder {

	@Autowired private GetUsuarioLogadoCmd getUsuarioLogadoCmd;
	@Autowired private TributosTOBuilder tributoTOBuilder;
	@Autowired private GetTributoCmd getTributoCmd;

	public TributosMovimentacoesTO buildTO(TributosMovimentacoes entity) {
		TributosMovimentacoesTO to = new TributosMovimentacoesTO();
		if (Objects.isNull(entity)) {
			return to;
		}
		
		BeanUtils.copyProperties(entity, to);
		
		to.setTributo(tributoTOBuilder.buildTO(entity.getTributo()));
		return to;
	}

	public List<TributosMovimentacoesTO> buildAll(List<TributosMovimentacoes> lista) {
		return lista.stream()
				.map(this::buildTO)
				.collect(Collectors.toList());
	}

	public TributosMovimentacoes build(TributosMovimentacoesTO to) {
		TributosMovimentacoes entity = new TributosMovimentacoes();

		BeanUtils.copyProperties(to, entity);
		
		if(Objects.nonNull(to.getTributo()) && Objects.nonNull(to.getTributo().getId())) {
			entity.setTributo(getTributoCmd.getById(to.getTributo().getId()));
		}
		entity.setUsuarioAlteracao(getUsuarioLogadoCmd.getUsuarioLogado().getIdUsuario());

		return entity;
	}

}
