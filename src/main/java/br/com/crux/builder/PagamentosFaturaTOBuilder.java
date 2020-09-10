package br.com.crux.builder;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.cmd.GetContasBancariaCmd;
import br.com.crux.cmd.GetReembolsosPagamentosCmd;
import br.com.crux.cmd.GetUsuarioLogadoCmd;
import br.com.crux.entity.ContasBancaria;
import br.com.crux.entity.Movimentacoes;
import br.com.crux.entity.PagamentosFatura;
import br.com.crux.to.PagamentosFaturaTO;
import br.com.crux.to.ReembolsosPagamentosTO;

@Component
public class PagamentosFaturaTOBuilder {

	@Autowired private GetUsuarioLogadoCmd getUsuarioLogadoCmd;
	@Autowired private GetContasBancariaCmd getContasBancariaCmd;
	@Autowired private ContasBancariaTOBuilder contasBancariaTOBuilder;
	@Autowired private GetReembolsosPagamentosCmd getReembolsosPagamentosCmd;

	
	public PagamentosFaturaTO buildTO(PagamentosFatura entity) {
		PagamentosFaturaTO to = new PagamentosFaturaTO();

		BeanUtils.copyProperties(entity, to);

		to.setContaBancaria(contasBancariaTOBuilder.buildTOCombo(entity.getContaBancaria()));
		
		List<ReembolsosPagamentosTO> reembolsosTO = getReembolsosPagamentosCmd.getReembolsoPagamentoTOByIdPagamentoFatura(entity.getId());
		to.setReembolsos(reembolsosTO);

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
		
		entity.setUsuarioAlteracao(getUsuarioLogadoCmd.getUsuarioLogado().getIdUsuario());

		return entity;
	}

}
