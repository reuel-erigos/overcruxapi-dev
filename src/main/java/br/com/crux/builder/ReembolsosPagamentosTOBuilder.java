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
import br.com.crux.entity.ReembolsosPagamentos;
import br.com.crux.to.ReembolsosPagamentosTO;

@Component
public class ReembolsosPagamentosTOBuilder {

	@Autowired private GetUsuarioLogadoCmd getUsuarioLogadoCmd;
	@Autowired private GetContasBancariaCmd getContasBancariaCmd;
	@Autowired private ContasBancariaTOBuilder contasBancariaTOBuilder;

	public ReembolsosPagamentosTO buildTO(ReembolsosPagamentos entity) {
		ReembolsosPagamentosTO to = new ReembolsosPagamentosTO();

		BeanUtils.copyProperties(entity, to);
		to.setContaBancaria(contasBancariaTOBuilder.buildTOCombo(entity.getContaBancaria()));
		to.setContaBancariaDestino(contasBancariaTOBuilder.buildTOCombo(entity.getContaBancariaDestino()));

		return to;
	}

	public List<ReembolsosPagamentosTO> buildAll(List<ReembolsosPagamentos> lista) {
		return lista.stream()
				.map(this::buildTO)
				.collect(Collectors.toList());
	}

	public ReembolsosPagamentos build(PagamentosFatura movimentacoes, ReembolsosPagamentosTO to) {
		ReembolsosPagamentos entity = new ReembolsosPagamentos();

		BeanUtils.copyProperties(to, entity);

		if (Objects.nonNull(to.getContaBancaria()) && Objects.nonNull(to.getContaBancaria().getId())) {
			ContasBancaria conta = getContasBancariaCmd.getById(to.getContaBancaria().getId());
			entity.setContaBancaria(conta);
		}
		
		if (Objects.nonNull(to.getContaBancariaDestino()) && Objects.nonNull(to.getContaBancariaDestino().getId())) {
			ContasBancaria conta = getContasBancariaCmd.getById(to.getContaBancariaDestino().getId());
			entity.setContaBancariaDestino(conta);
		}
		
		entity.setUsuarioAlteracao(getUsuarioLogadoCmd.getUsuarioLogado().getIdUsuario());

		return entity;
	}

}
