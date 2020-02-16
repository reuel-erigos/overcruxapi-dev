package br.com.crux.builder;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.cmd.GetCategoriasContabeisCmd;
import br.com.crux.cmd.GetDepartamentoCmd;
import br.com.crux.cmd.GetMaterialCmd;
import br.com.crux.cmd.GetUnidadeCmd;
import br.com.crux.cmd.GetUsuarioLogadoCmd;
import br.com.crux.entity.Fatura;
import br.com.crux.entity.Movimentacoes;
import br.com.crux.to.FaturaTO;

@Component
public class FaturaTOBuilder {

	@Autowired CategoriasContabeisTOBuilder categoriasContabeisTOBuilder;
	@Autowired DepartamentoTOBuilder departamentoTOBuilder;
	@Autowired MaterialTOBuilder materialTOBuilder;
	@Autowired PedidosMateriaisTOBuilder pedidosMateriaisTOBuilder;
	@Autowired UnidadeTOBuilder unidadeTOBuilder;
	@Autowired GetUsuarioLogadoCmd getUsuarioLogadoCmd;
	@Autowired GetCategoriasContabeisCmd getCategoriasContabeisCmd;
	@Autowired GetDepartamentoCmd getDepartamentoCmd;
	@Autowired GetMaterialCmd getMaterialCmd;
	@Autowired GetUnidadeCmd getUnidadeCmd;

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
		entity.setUsuarioAlteracao(getUsuarioLogadoCmd.getUsuarioLogado().getIdUsuario());

		return entity;
	}

}
