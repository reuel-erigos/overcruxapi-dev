package br.com.crux.builder;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.cmd.GetUsuarioLogadoCmd;
import br.com.crux.entity.Fatura;
import br.com.crux.entity.Movimentacoes;
import br.com.crux.to.FaturaTO;

@Component
public class FaturaTOBuilder {

	@Autowired GetUsuarioLogadoCmd getUsuarioLogadoCmd;

	public FaturaTO buildTO(Fatura entity) {
		FaturaTO to = new FaturaTO();
		BeanUtils.copyProperties(entity, to);
		return to;
	}

	public List<FaturaTO> buildAll(List<Fatura> lista) {
		return lista.stream()
				.map(this::buildTO)
				.collect(Collectors.toList());
	}

	public Fatura build(Movimentacoes movimentacoes, FaturaTO faturaTO) {
		Fatura entity = new Fatura();

		BeanUtils.copyProperties(faturaTO, entity);
		entity.setMovimentacao(movimentacoes);
		entity.setUsuarioAlteracao(getUsuarioLogadoCmd.getUsuarioLogado()
				.getIdUsuario());

		return entity;
	}

}
