package br.com.crux.builder;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.cmd.GetContasBancariaCmd;
import br.com.crux.cmd.GetUsuarioLogadoCmd;
import br.com.crux.entity.ContasBancaria;
import br.com.crux.entity.PagamentosFatura;
import br.com.crux.entity.RateiosPagamentos;
import br.com.crux.to.RateiosPagamentosTO;

@Component
public class RateiosPagamentosTOBuilder {

	@Autowired private GetUsuarioLogadoCmd getUsuarioLogadoCmd;
	@Autowired private GetContasBancariaCmd getContasBancariaCmd;
	@Autowired private ContasBancariaTOBuilder contasBancariaTOBuilder;

	public RateiosPagamentosTO buildTO(RateiosPagamentos entity) {
		RateiosPagamentosTO to = new RateiosPagamentosTO();

		BeanUtils.copyProperties(entity, to);
		to.setContaBancaria(contasBancariaTOBuilder.buildTOCombo(entity.getContaBancaria()));

		return to;
	}

	public List<RateiosPagamentosTO> buildAll(List<RateiosPagamentos> lista) {
		return lista.stream()
				.map(this::buildTO)
				.collect(Collectors.toList());
	}

	public RateiosPagamentos build(PagamentosFatura movimentacoes, RateiosPagamentosTO to) {
		RateiosPagamentos entity = new RateiosPagamentos();

		BeanUtils.copyProperties(to, entity);

		if (Objects.nonNull(to.getContaBancaria()) && Objects.nonNull(to.getContaBancaria().getId())) {
			ContasBancaria conta = getContasBancariaCmd.getById(to.getContaBancaria().getId());
			entity.setContaBancaria(conta);
		}
		
		entity.setUsuarioAlteracao(getUsuarioLogadoCmd.getUsuarioLogado().getIdUsuario());

		return entity;
	}

}
