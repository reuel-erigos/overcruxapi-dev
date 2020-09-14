package br.com.crux.builder;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.cmd.GetTributoCmd;
import br.com.crux.cmd.GetUsuarioLogadoCmd;
import br.com.crux.entity.TributosItensMovimentacoes;
import br.com.crux.to.TributosItensMovimentacoesTO;

@Component
public class TributosItensMovimentacaoTOBuilder {

	@Autowired private GetUsuarioLogadoCmd getUsuarioLogadoCmd;
	@Autowired private TributosTOBuilder tributoTOBuilder;
	@Autowired private GetTributoCmd getTributoCmd;

	public TributosItensMovimentacoesTO buildTO(TributosItensMovimentacoes entity) {
		TributosItensMovimentacoesTO to = new TributosItensMovimentacoesTO();
		if (Objects.isNull(entity)) {
			return to;
		}
		
		BeanUtils.copyProperties(entity, to);
		
		to.setTributo(tributoTOBuilder.buildTO(entity.getTributo()));
		return to;
	}

	public List<TributosItensMovimentacoesTO> buildAll(List<TributosItensMovimentacoes> lista) {
		return lista.stream()
				.map(this::buildTO)
				.collect(Collectors.toList());
	}

	public TributosItensMovimentacoes build(TributosItensMovimentacoesTO to) {
		TributosItensMovimentacoes entity = new TributosItensMovimentacoes();

		BeanUtils.copyProperties(to, entity);
		
		if(Objects.nonNull(to.getTributo()) && Objects.nonNull(to.getTributo().getId())) {
			entity.setTributo(getTributoCmd.getById(to.getTributo().getId()));
		}
		entity.setUsuarioAlteracao(getUsuarioLogadoCmd.getUsuarioLogado().getIdUsuario());

		return entity;
	}

}
