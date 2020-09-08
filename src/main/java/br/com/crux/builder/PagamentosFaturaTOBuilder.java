package br.com.crux.builder;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.cmd.GetContasBancariaCmd;
import br.com.crux.cmd.GetFaturaCmd;
import br.com.crux.cmd.GetUsuarioLogadoCmd;
import br.com.crux.entity.ContasBancaria;
import br.com.crux.entity.Movimentacoes;
import br.com.crux.entity.PagamentosFatura;
import br.com.crux.to.PagamentosFaturaTO;

@Component
public class PagamentosFaturaTOBuilder {

	@Autowired GetUsuarioLogadoCmd getUsuarioLogadoCmd;
	@Autowired GetFaturaCmd getFaturaCmd;
	@Autowired GetContasBancariaCmd getContasBancariaCmd;
	@Autowired ContasBancariaTOBuilder contasBancariaTOBuilder;
	@Autowired FaturaTOBuilder faturaTOBuilder;
	@Autowired SaldosContasBancariaTOBuilder saldosContasBancariaTOBuilder;

	public PagamentosFaturaTO buildTO(PagamentosFatura entity) {
		PagamentosFaturaTO to = new PagamentosFaturaTO();

		BeanUtils.copyProperties(entity, to);

		to.setContaBancaria(contasBancariaTOBuilder.buildTOCombo(entity.getContaBancaria()));
		to.setContaReembolso(contasBancariaTOBuilder.buildTOCombo(entity.getContaReembolso()));

		return to;
	}

	public List<PagamentosFaturaTO> buildAll(List<PagamentosFatura> lista) {
		return lista.stream()
				.map(this::buildTO)
				.collect(Collectors.toList());
	}

	public PagamentosFatura build(Movimentacoes movimentacoes, PagamentosFaturaTO to) {
		PagamentosFatura entity = new PagamentosFatura();

		BeanUtils.copyProperties(to, entity);

		if (Objects.nonNull(to.getContaBancaria()) && Objects.nonNull(to.getContaBancaria().getId())) {
			ContasBancaria conta = getContasBancariaCmd.getById(to.getContaBancaria().getId());
			entity.setContaBancaria(conta);
		}
		if (Objects.nonNull(to.getContaReembolso()) && Objects.nonNull(to.getContaReembolso().getId())) {
			ContasBancaria conta = getContasBancariaCmd.getById(to.getContaReembolso().getId());
			entity.setContaReembolso(conta);
		}
		
		entity.setUsuarioAlteracao(getUsuarioLogadoCmd.getUsuarioLogado().getIdUsuario());

		return entity;
	}

}
