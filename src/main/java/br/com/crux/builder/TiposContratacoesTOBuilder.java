package br.com.crux.builder;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.cmd.GetUsuarioLogadoCmd;
import br.com.crux.entity.TiposContratacoes;
import br.com.crux.to.TiposContratacoesTO;

@Component
public class TiposContratacoesTOBuilder {

	@Autowired GetUsuarioLogadoCmd getUsuarioLogadoCmd;

	public TiposContratacoes build(TiposContratacoesTO to) {
		TiposContratacoes retorno = new TiposContratacoes();

		BeanUtils.copyProperties(to, retorno);

		
		retorno.setUsuarioAlteracao(getUsuarioLogadoCmd.getUsuarioLogado().getIdUsuario());

		return retorno;

	}

	public TiposContratacoesTO buildTO(TiposContratacoes tiposContratacoes) {
		TiposContratacoesTO retorno = new TiposContratacoesTO();
		
		if(Objects.isNull(tiposContratacoes)) {
			return retorno;
		}

		BeanUtils.copyProperties(tiposContratacoes, retorno);

		return retorno;

	}

	public List<TiposContratacoesTO> buildAll(List<TiposContratacoes> dtos) {
		return dtos.stream().map(this::buildTO).collect(Collectors.toList());
	}

}
